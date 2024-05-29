import java.util.*;
import java.util.Comparator;

public class User {
    private String username;
    private String password;
    private boolean role;
    private Vector<Movie> userMovies = new Vector<Movie>();
    private Map<String, Integer> favoriteGenres = new TreeMap<>();

    public User(String username, String password, String role, Vector<Movie> userMovies) {
        this.username = username;
        this.password = password;
        this.role = Boolean.parseBoolean(role);
        this.userMovies = userMovies;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getRole() {
        return role;
    }

    public void feed() {
        for (Movie it : userMovies) {
            it.summaryPrint();
        }
    }

    public void sortDate() {
        Collections.sort(userMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie1, Movie movie2) {
                return movie2.getDate().compareTo(movie1.getDate());
            }
        });
    }

    public void initializeFavorites() {
        for (Movie i : userMovies) {
            favoriteGenres.put(i.getGenre(), 0);
        }
    }

    public void sortFavorites() {
        favoriteGenres.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> favoriteGenres.put(x.getKey(), x.getValue()));
    }

    public void updateFavorites(Movie movie) {
        if (favoriteGenres.get(movie.getGenre()) == null) {
            favoriteGenres.put(movie.getGenre(), 0);
            sortFavorites();
        }
        Collections.sort(userMovies, new GenreComparator(favoriteGenres));
    }

    public void incrementFavorite(Movie movie) {
        favoriteGenres.put(movie.getGenre(), favoriteGenres.get(movie.getGenre()) + 1);
        sortFavorites();
        Collections.sort(userMovies, new GenreComparator(favoriteGenres));
    }

    public void decrementFavorite(Movie movie) {
        favoriteGenres.put(movie.getGenre(), favoriteGenres.get(movie.getGenre()) - 1);
        sortFavorites();
        Collections.sort(userMovies, new GenreComparator(favoriteGenres));
    }

    public class GenreComparator implements Comparator<Movie> {
        private Map<String, Integer> favoriteGenres;

        public GenreComparator(Map<String, Integer> favoriteGenres) {
            this.favoriteGenres = favoriteGenres;
        }

        @Override
        public int compare(Movie movie1, Movie movie2) {
            int genre1 = favoriteGenres.getOrDefault(movie1.getGenre(), Integer.MIN_VALUE);
            int genre2 = favoriteGenres.getOrDefault(movie2.getGenre(), Integer.MIN_VALUE);
            return Integer.compare(genre2, genre1);
        }
    }
}