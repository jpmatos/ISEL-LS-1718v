package pt.isel.ls.tables;

public class Tickets {
    private int id, sid, seat;
    private String row;

    public Tickets(){}

    public Tickets(int id, String row, int seat, int sid){
        this.id = id;
        this.row = row;
        this.seat = seat;
        this.sid = sid;
    }

    public Tickets(int tickets) {
        this.id = tickets;
    }

    public int getId() {
        return id;
    }
    public int getSid() {
        return sid;
    }
    public int getSeat() {
        return seat;
    }
    public String getRow() {
        return row;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public void setRow(String row) {
        this.row = row;
    }


    @Override
    public String toString() {
        return "Tickets{" +
                "id="+ getId()+
                ", row="+ getRow()+
                ", seat="+ getSeat()+
                ", sid="+ getSid()+
                "}";
    }

    public boolean equalsTest(Tickets t){
        return this.getId()==t.getId() &&
                this.getRow().equals(t.getRow()) &&
                this.getSeat()==t.getSeat() &&
                this.getSid()==t.getSid();
    }
}
