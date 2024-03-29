package pl.cinema.objects;

public class CinemaSeat {
    private int row;
    private int column;
    private int price;

    public CinemaSeat(int row, int column) {
        this.row = row;
        this.column = column;
        getPrice(row);
    }

    public void getPrice(int row) {
        if (row <= 4) {
            setPrice(10);
        } else {
            setPrice(8);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
