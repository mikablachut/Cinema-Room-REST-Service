package pl.cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class CinemaController {
    CinemaRoom cinema = new CinemaRoom();

    ArrayList<CinemaTicket> cinemaTickets = new ArrayList<>();
    ArrayList<CinemaSeat> availableSeats = cinema.createCinemaRoom(9,9);
    CinemaRoom cinemaRoom = new CinemaRoom(9,9,availableSeats);

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public CinemaTicket purchaseTicket(@RequestBody CinemaSeat cinemaSeat) {
        if (cinemaSeat.getRow() > 9 || cinemaSeat.getColumn() > 9) {
            throw new CinemaSeatNotFoundException();
        } else if (cinemaSeat.getRow() <= 0 || cinemaSeat.getColumn() <= 0) {
            throw new CinemaSeatNotFoundException();
        } else {
            for (CinemaSeat seat : availableSeats) {
                if (seat.getRow() == cinemaSeat.getRow() && seat.getColumn() == cinemaSeat.getColumn()) {
                    availableSeats.remove(seat);
                    cinemaTickets.add(new CinemaTicket(UUID.randomUUID(), cinemaSeat));
                    for (CinemaTicket ticket : cinemaTickets) {
                        if (ticket.getTicket().getRow() == cinemaSeat.getRow() && ticket.getTicket().getColumn() ==
                        cinemaSeat.getColumn()) {
                            return ticket;
                        }
                    }
                }
            }
        }
        throw new CinemaPurchaseNotFoundException();
    }

    @PostMapping("/return")
    public CinemaReturnedTicket returnTicket(@RequestBody CinemaTicket cinemaTicket) {
        for (CinemaTicket ticket : cinemaTickets) {
            if (cinemaTicket.getToken().equals(ticket.getToken())) {
                CinemaReturnedTicket cinemaReturnedTicket = new CinemaReturnedTicket(ticket.getTicket());
                availableSeats.add(new CinemaSeat(ticket.getTicket().getRow(),ticket.getTicket().getColumn()));
                cinemaTickets.remove(ticket);
                return cinemaReturnedTicket;
            }
        }
        throw new CinemaExpireTokenException();
    }
}
