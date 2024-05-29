import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Utils {
    private Vector<Movie> movies = new Vector<Movie>();
    private Vector<User> users = new Vector<User>();
    private User curentUser;

    public Utils() throws FileNotFoundException {
        File fileMovies = new File("/Users/George Patrasc/Desktop/JavaCred/JavaCred/movies.txt");
        Scanner scanMovies = new Scanner(fileMovies);
        while (scanMovies.hasNextLine()) {
            String title = scanMovies.nextLine();
            String date = scanMovies.nextLine();
            String genere = scanMovies.nextLine();
            String rating = scanMovies.nextLine();
            String numberOfVotes = scanMovies.nextLine();
            String creator = scanMovies.nextLine();
            String runtime = scanMovies.nextLine();
            String parentalGuide = scanMovies.nextLine();
            Movie movie = new Movie(title, date, genere, rating, numberOfVotes, creator, runtime, parentalGuide);
            movies.add(movie);
        }
        File fileUsers = new File("/Users/George Patrasc/Desktop/JavaCred/JavaCred/users.txt");
        Scanner scanUsers = new Scanner(fileUsers);
        while (scanUsers.hasNextLine()) {
            String username = scanUsers.nextLine();
            String password = scanUsers.nextLine();
            String role = scanUsers.nextLine();
            User user = new User(username, password, role, movies);
            user.initializeFavorites();
            users.add(user);
        }
    }

    public Vector<Movie> getMovies() {
        return movies;
    }

    public User getCurentUser() {
        return curentUser;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateFavorites(Movie movie) {
        curentUser.updateFavorites(movie);
    }

    public void feed() {
        curentUser.feed();
    }

    public void sortDate() {
        curentUser.sortDate();
    }

    public void incrementFavorite(Movie movie) {
        curentUser.incrementFavorite(movie);
    }

    public void decrementFavorite(Movie movie) {
        curentUser.decrementFavorite(movie);
    }

    public Optional<Movie> search(String title) {
        return movies.stream()
                .filter(movie -> title.equalsIgnoreCase(movie.getTitle()))
                .findFirst();
    }

    public boolean verifyAccount(String username, String password) {
        for (User i : users) {
            if (Objects.equals(i.getUsername(), username) && Objects.equals(i.getPassword(), password)) {
                curentUser = i;
                return true;
            }
        }
        return false;
    }

    public boolean searchUser(String username) {
        for (User i : users) {
            if (Objects.equals(i.getUsername(), username)) {
                return true;
            }
        }
        return false;
    }
}