package pl.cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CinemaController {
    CinemaRoom cinema = new CinemaRoom();
    ArrayList<CinemaSeat> availableSeats = cinema.createCinemaRoom(9,9);
    CinemaRoom cinemaRoom = new CinemaRoom(9,9,availableSeats);

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @PostMapping("/purchase")
    public CinemaSeat purchaseTicket(@RequestBody CinemaSeat cinemaSeat) {
        if (cinemaSeat.getRow() > 9 || cinemaSeat.getColumn() > 9) {
            throw new CinemaSeatNotFoundException();
        } else {
            for (CinemaSeat seat : availableSeats) {
                if (seat.getRow() == cinemaSeat.getRow() && seat.getColumn() == cinemaSeat.getColumn()) {
                    availableSeats.remove(seat);
                    return cinemaSeat;
                }
            }
        }
        throw new CinemaPurchaseNotFoundException();
    }
}
