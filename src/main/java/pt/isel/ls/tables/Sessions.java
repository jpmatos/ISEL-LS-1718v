package pt.isel.ls.tables;

public class Sessions {
    private int iD, mid, tid, cid;
    private String date;

    public Sessions() {
    }

    public Sessions(int iD, String date, int mid, int tid, int cid) {
        this.iD = iD;
        this.mid = mid;
        this.tid = tid;
        this.cid = cid;
        this.date = date;
    }

    public Sessions(int sessions) {
        this.iD = sessions;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public int getiD() {
        return iD;
    }
    public int getMid() {
        return mid;
    }
    public int getTid() {
        return tid;
    }
    public int getCid() {
        return cid;
    }
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "_iD=" + iD +
                ", _date=" + date +
                ", _mid=" + mid +
                ", _tid=" + tid +
                ", _cid=" + cid +
                '}';
    }

    public boolean equalsTest(Sessions sessions) {
        return  sessions.getMid() == this.mid &&
                sessions.getCid() == this.cid &&
                sessions.getTid() == this.tid;
    }
}
