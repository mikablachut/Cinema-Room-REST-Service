package pl.cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {
    private final CinemaRoom cinema = new CinemaRoom();
    private final CinemaRoom cinemaRoom = new CinemaRoom(9,9, cinema.getAvailableSeats());

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }
}
