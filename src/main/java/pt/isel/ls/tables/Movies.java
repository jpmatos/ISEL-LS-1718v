package pt.isel.ls.tables;

public class Movies {
    private int iD, releaseYear, duration;
    private String title;

    public Movies() {
    }

    public Movies(int iD, String title, int releaseYear, int duration) {
        this.iD = iD;
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
    }

    public Movies(int movies) {
        this.iD = movies;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getiD() {
        return iD;
    }
    public String getTitle() {
        return title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "_iD=" + iD +
                ", _title=" + title +
                ", _releaseYear=" + releaseYear +
                ", _duration=" + duration +
                '}';
    }

    public boolean equalsTest(Movies movies) {
        return movies.getTitle().equals(this.title) &&
               movies.getReleaseYear() == this.releaseYear &&
               movies.getDuration() == this.duration;
    }
}
