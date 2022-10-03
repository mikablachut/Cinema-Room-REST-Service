package pl.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.cinema.controller.CinemaController;
import pl.cinema.objects.CinemaSeat;
import pl.cinema.objects.CinemaTicket;
import java.util.ArrayList;
import java.util.UUID;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CinemaControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CinemaController cinemaController;

    private static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @Test
    public void getCurrentIncomeTest() {
        ArrayList<CinemaTicket> cinemaTickets = new ArrayList<>();
        cinemaTickets.add(new CinemaTicket(UUID.randomUUID(), new CinemaSeat(3,4)));
        cinemaTickets.add(new CinemaTicket(UUID.randomUUID(), new CinemaSeat(8,6)));

        assertEquals(18, cinemaController.getCurrentIncome(cinemaTickets));
    }

    @Test
    public void getCinemaRoomHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/seats")).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.total_rows", is(9)))
                .andExpect(jsonPath("$.total_columns", is(9)))
                .andExpect(jsonPath("$.available_seats", hasSize(81)));
    }

    @Test
    public void purchaseTicketHttpRequest() throws Exception {
        CinemaSeat cinemaSeat = new CinemaSeat(9,5);

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cinemaSeat))).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.ticket.row", is(9)))
                .andExpect(jsonPath("$.ticket.column", is(5)))
                .andExpect(jsonPath("$.ticket.price", is(8)));
    }

    @Test
    public void purchaseTicketTestSeatNotExistErrorResponse() throws Exception {
        CinemaSeat wrongRow = new CinemaSeat(10,5);
        CinemaSeat wrongColumn = new CinemaSeat(5,10);
        CinemaSeat rowEqualToZero = new CinemaSeat(0,4);
        CinemaSeat columnLesserThanZero = new CinemaSeat(5,-1);

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongRow))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The number of a row or a column is out of bounds!")));

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongColumn))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The number of a row or a column is out of bounds!")));

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(rowEqualToZero))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The number of a row or a column is out of bounds!")));

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(columnLesserThanZero))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The number of a row or a column is out of bounds!")));

    }

    @Test
    public void purchaseTicketHttpRequestTicketNotFoundException() throws Exception {
        CinemaSeat cinemaSeat = new CinemaSeat(7,5);

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinemaSeat))).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.ticket.row", is(7)))
                .andExpect(jsonPath("$.ticket.column", is(5)))
                .andExpect(jsonPath("$.ticket.price", is(8)));

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinemaSeat))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("The ticket has been already purchased!")));
    }

    @Test
    public void returnTicketHttpRequestErrorResponse() throws Exception {
        CinemaTicket cinemaTicketToSave = new CinemaTicket(UUID.fromString("7ee496bc-131d-45cd-8aa7-bbab5d2c52a3"),
                new CinemaSeat(7,5));

        mockMvc.perform(post("/return").contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(cinemaTicketToSave))).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error",is("Wrong token!")));

    }

    @Test
    public void getStatisticsHttpRequest() throws Exception {
        CinemaSeat cinemaSeat = new CinemaSeat(8,5);

        mockMvc.perform(post("/purchase").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinemaSeat))).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.ticket.row", is(8)))
                .andExpect(jsonPath("$.ticket.column", is(5)))
                .andExpect(jsonPath("$.ticket.price", is(8)));

        mockMvc.perform(post("/stats").contentType(MediaType.APPLICATION_JSON)
                .param("password","super_secret")).andExpect(status().isOk())
                .andExpect(jsonPath("$.current_income",is(8)))
                .andExpect(jsonPath("$.number_of_available_seats", is(80)))
                .andExpect(jsonPath("$.number_of_purchased_tickets", is(1)));
    }

    @Test
    public void getStatisticsHttpRequestErrorResponse() throws Exception {
        mockMvc.perform(post("/stats").contentType(MediaType.APPLICATION_JSON)
                .param("password","secret")).andExpect(status().is(401))
                .andExpect(jsonPath("$.error", is("The password is wrong!")));

        mockMvc.perform(post("/stats").contentType(MediaType.APPLICATION_JSON)
                        .param("password","")).andExpect(status().is(401))
                .andExpect(jsonPath("$.error", is("The password is wrong!")));

        mockMvc.perform(post("/stats")).andExpect(status().is(401))
                .andExpect(jsonPath("$.error", is("The password is wrong!")));
    }
}
