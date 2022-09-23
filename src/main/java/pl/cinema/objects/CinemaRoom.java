package pl.cinema.objects;

import java.util.ArrayList;

public class CinemaRoom {
    private int totalRows;
    private int totalColumns;

    private ArrayList<CinemaSeat> availableSeats;

    public CinemaRoom() {

    }

    public CinemaRoom(int totalRows, int totalColumns, ArrayList<CinemaSeat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public ArrayList<CinemaSeat> createCinemaRoom(int totalColumns, int totalRows) {
        ArrayList<CinemaSeat> availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new CinemaSeat(i,j));
            }
        }
        return availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public ArrayList<CinemaSeat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(ArrayList<CinemaSeat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}

