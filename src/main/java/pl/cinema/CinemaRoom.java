package pl.cinema;

import java.util.List;

public class CinemaRoom {
    private int totalRows;
    private int totalColumns;
    private List<CinemaSeat> availableSeats;

    public CinemaRoom(int totalRows, int totalColumns, List<CinemaSeat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
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

    public List<CinemaSeat> getAvailableSeats() {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                availableSeats.add(new CinemaSeat(i,j));
            }
        }
        return availableSeats;
    }

    public void setAvailableSeats(List<CinemaSeat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
