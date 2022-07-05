package pl.cinema;

import org.springframework.web.bind.annotation.GetMapping;
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
}
