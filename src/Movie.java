import java.time.Year;

public class Movie {
    private String title;
    private Year date;
    private String genre;
    private String rating;
    private Integer numberOfVotes;
    private String creator;
    private Integer runtime;
    private String parentalGuide;

    public Movie(String title, String date, String genre, String rating, String numberOfVotes, String creator, String runtime, String parentalGuide) {
        this.title = title;
        this.date = Year.parse(date);
        this.genre = genre;
        this.rating = rating;
        this.numberOfVotes = Integer.valueOf(numberOfVotes);
        this.creator = creator;
        this.runtime = Integer.valueOf(runtime);
        this.parentalGuide = parentalGuide;
    }

    public String getTitle() {
        return title;
    }

    public Year getDate() {
        return date;
    }

    public String getGenre() {
        return genre;
    }

    public void summaryPrint() {
        System.out.println(title + " (" + date + ")");
    }

    @Override
    public String toString() {
        return '\n' +
                "Title: " + title + '\n' +
                "Date: " + date + '\n' +
                "Genere: " + genre + '\n' +
                "Rating: " + rating + '\n' +
                "Number of Votes: " + numberOfVotes + '\n' +
                "Creator: " + creator + '\n' +
                "Runtime: " + runtime + '\n' +
                "ParentalGuide: " + parentalGuide + "+" + '\n';
    }
}