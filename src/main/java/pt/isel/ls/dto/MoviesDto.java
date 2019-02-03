package pt.isel.ls.dto;

public class MoviesDto {
    private final int id;
    private final String original_title;
    private final String tagline;
    private final String overview;
    private final double vote_average;
    private final String release_date;

    public MoviesDto(int id, String original_title, String tagline, String overview, double vote_average, String release_date) {
        this.id = id;
        this.original_title = original_title;
        this.tagline = tagline;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public String getReleaseDate() {
        return release_date;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", original_title='" + original_title + '\'' +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
