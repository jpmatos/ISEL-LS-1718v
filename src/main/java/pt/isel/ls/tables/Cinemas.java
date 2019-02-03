package pt.isel.ls.tables;

public class Cinemas {

    private int iD;
    private String name, city;

    public Cinemas() {}

    public Cinemas(int iD, String name, String city) {
        this.iD = iD;
        this.name = name;
        this.city = city;
    }

    public Cinemas(int cinemas) {
        this.iD = cinemas;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getiD() {
        return iD;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Cinemas{" +
                "_iD=" + iD +
                ", _name=" + name +
                ", _city=" + city +
                '}';
    }

    public boolean equalsTest(Cinemas cinemas) {
        return cinemas.getName().equals(this.name) &&
                cinemas.getCity().equals(this.city);
    }
}
