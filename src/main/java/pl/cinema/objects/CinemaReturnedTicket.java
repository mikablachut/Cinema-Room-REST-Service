package pl.cinema.objects;

public class CinemaReturnedTicket {
    private CinemaSeat returnedTicket;

    public CinemaReturnedTicket() {

    }

    public CinemaReturnedTicket(CinemaSeat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public CinemaSeat getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(CinemaSeat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
