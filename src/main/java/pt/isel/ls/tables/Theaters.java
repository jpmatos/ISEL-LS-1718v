package pt.isel.ls.tables;

public class Theaters {
    private int iD, rows, availableSeats, seats, cid;
    private String name;

    public Theaters() {
    }

    public Theaters(int iD, String name, int rows, int availableSeats, int seats, int cid) {
        this.iD = iD;
        this.rows = rows;
        this.availableSeats = availableSeats;
        this.seats = seats;
        this.cid = cid;
        this.name = name;
    }

    public Theaters(int theaters) {
        this.iD = theaters;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getiD() {
        return iD;
    }
    public int getRows() {
        return rows;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public int getSeats() {
        return seats;
    }
    public int getCid() {
        return cid;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Theaters{" +
                "_iD=" + iD +
                ", _name=" + name +
                ", _rows=" + rows +
                ", _availableSeats=" + availableSeats +
                ", _seats=" + seats +
                ", _cid=" + cid +
                '}';
    }

    public boolean equalsTest(Theaters theaters) {
        return theaters.getName().equals(this.name) &&
                theaters.getRows() == this.rows &&
                theaters.getSeats() == this.seats &&
                theaters.getCid() == this.cid;
    }
}
