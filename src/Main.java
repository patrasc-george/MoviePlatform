import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void signIn(Utils u) {
        int x = 0;
        String aux, username, password;
        Scanner scan = new Scanner(System.in);
        while (x != 3) {
            System.out.println("\nLog in with your IMDb account to continue");
            System.out.println("      1. Log in        2. Sign Up");
            System.out.println("                                  3. Exit");
            aux = scan.nextLine();
            System.out.println();
            x = Integer.parseInt(aux);
            switch (x) {
                case 1:
                    System.out.println("Username: ");
                    username = scan.nextLine();
                    System.out.println("Password: ");
                    password = scan.nextLine();
                    if (u.verifyAccount(username, password)) {
                        System.out.println("Login successful.");
                        System.out.println("\n" + "Welcome back, " + username + "!");
                        x = 3;
                    } else {
                        System.out.println("Error: Could not login.");
                    }
                    break;
                case 2:
                    System.out.println("Username: ");
                    username = scan.nextLine();
                    System.out.println("Password: ");
                    password = scan.nextLine();
                    if (u.searchUser(username)) {
                        System.out.println("Error: Existing username.");
                    } else {
                        try {
                            User user = new User(username, password, "false", u.getMovies());
                            u.addUser(user);
                            BufferedWriter out = new BufferedWriter(new FileWriter("users.txt", true));
                            out.write("\n" + username + "\n" + password + "\n" + false);
                            out.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        System.out.println("Registration successful.\n");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Utils u = new Utils();
        signIn(u);
        Scanner scan = new Scanner(System.in);
        int x = 0;
        String aux, answer;
        while (x != -1) {
            System.out.println();
            u.feed();
            System.out.println("\nIf you want to filter the movies by release date, press key 1.");
            System.out.println("If you want to select a movie, press key 2.");
            System.out.println("If you want to add a movie, press key 3.");
            System.out.println("If you want to log out, press key 4.");
            aux = scan.nextLine();
            x = Integer.parseInt(aux);
            switch (x) {
                case 1:
                    u.sortDate();
                    break;
                case 2:
                    System.out.println("Search ");
                    aux = scan.nextLine();
                    System.out.println();
                    if (!u.search(aux).isPresent()) {
                        System.out.println("The movie does not exist.");
                        break;
                    }
                    System.out.println(u.search(aux));
                    System.out.println("If you want to rate this movie, press key 1.");
                    System.out.println("If you want to go back, press key 2.");
                    answer = scan.nextLine();
                    x = Integer.parseInt(answer);
                    if (x == 1) {
                        System.out.println("Do you like this movie?\n(Yes / No)");
                        answer = scan.nextLine();
                        if (answer.equalsIgnoreCase("Yes")) {
                            u.incrementFavorite(u.search(aux).get());
                        } else {
                            u.decrementFavorite(u.search(aux).get());
                        }
                    }
                    break;
                case 3:
                    if (!u.getCurentUser().getRole()) {
                        System.out.println("\nYou cannot add a movie because you are not an admin.");
                        break;
                    }
                    System.out.println("Title: ");
                    String title = scan.nextLine();
                    System.out.println("Date: ");
                    String date = scan.nextLine();
                    System.out.println("Genere: ");
                    String genere = scan.nextLine();
                    System.out.println("Rating: ");
                    String rating = scan.nextLine();
                    System.out.println("Number of Votes: ");
                    String numberOfVotes = scan.nextLine();
                    System.out.println("Creator: ");
                    String creator = scan.nextLine();
                    System.out.println("Runtime: ");
                    String runtime = scan.nextLine();
                    System.out.println("Parental Guide: ");
                    String parentalGuide = scan.nextLine();
                    try {
                        Movie movie = new Movie(title, date, genere, rating, numberOfVotes, creator, runtime, parentalGuide);
                        u.addMovie(movie);
                        u.updateFavorites(movie);
                        BufferedWriter out = new BufferedWriter(new FileWriter("movies.txt", true));
                        out.write("\n" + title + "\n" + date + "\n" + genere + "\n" + rating + "\n" + numberOfVotes + "\n" + creator + "\n" + runtime + "\n" + parentalGuide);
                        out.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    signIn(u);
                    break;
            }
        }
    }
}