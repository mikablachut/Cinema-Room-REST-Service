package pl.cinema;

import java.util.ArrayList;
import java.util.UUID;

public class CinemaTicket {
    private UUID token;
    private CinemaSeat ticket;

    public CinemaTicket() {
    }

    public CinemaTicket(UUID token, CinemaSeat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public CinemaSeat getTicket() {
        return ticket;
    }

    public void setTicket(CinemaSeat ticket) {
        this.ticket = ticket;
    }
}
