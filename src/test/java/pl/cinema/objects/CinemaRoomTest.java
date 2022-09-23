package pl.cinema.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CinemaRoomTest {

    @Test
    @DisplayName("testCreateCinemaRoom()")
    void compareCinemaRooms() {
        CinemaRoom cinemaRoom = new CinemaRoom();
        ArrayList<CinemaSeat> result = cinemaRoom.createCinemaRoom(3,2);
        ArrayList<CinemaSeat> expectedResult = cinemaSeatsFactory();
        assertEquals(result.get(0).getRow(), expectedResult.get(0).getRow());
        assertEquals(result.get(0).getColumn(), expectedResult.get(0).getColumn());
        assertEquals(result.get(1).getRow(), expectedResult.get(1).getRow());
        assertEquals(result.get(1).getColumn(), expectedResult.get(1).getColumn());
        assertEquals(result.get(2).getRow(), expectedResult.get(2).getRow());
        assertEquals(result.get(2).getColumn(), expectedResult.get(2).getColumn());
        assertEquals(result.get(3).getRow(), expectedResult.get(3).getRow());
        assertEquals(result.get(3).getColumn(), expectedResult.get(3).getColumn());
        assertEquals(result.get(4).getRow(), expectedResult.get(4).getRow());
        assertEquals(result.get(4).getColumn(), expectedResult.get(4).getColumn());
        assertEquals(result.get(5).getRow(), expectedResult.get(5).getRow());
        assertEquals(result.get(5).getColumn(), expectedResult.get(5).getColumn());
    }

    static ArrayList<CinemaSeat> cinemaSeatsFactory() {
        ArrayList<CinemaSeat> expectedCinemaSeats = new ArrayList<>();
        expectedCinemaSeats.add(0, new CinemaSeat(1, 1));
        expectedCinemaSeats.add(1, new CinemaSeat(1, 2));
        expectedCinemaSeats.add(2, new CinemaSeat(1, 3));
        expectedCinemaSeats.add(3, new CinemaSeat(2, 1));
        expectedCinemaSeats.add(4, new CinemaSeat(2, 2));
        expectedCinemaSeats.add(5, new CinemaSeat(2, 3));
        return expectedCinemaSeats;
    }
}