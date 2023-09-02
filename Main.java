import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import pkg.*;
public class Main {

    private static HttpURLConnection connection;
    private static final String API = "44bad9bbcbd79e746cedf9f960939772";

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException {
        int choice ;
        String name;
        Theater theater = new Theater();
        do{
            System.out.println("Welcome to the LUT Kino at Yliopistonkatu!");
            System.out.println("1. Add a movie");
            System.out.println("2. Remove a movie");
            System.out.println("3. View a movie");
            System.out.println("4. View all movies");
            System.out.println("5. Add a showtime");
            System.out.println("6. Remove a showtime");
            System.out.println("7. View a showtime");
            System.out.println("8. View all showtimes");
            System.out.println("9. Buy a ticket");
            System.out.println("10. View seating");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    theater.addMovie(GetValue());
                    System.out.println("Movie added successfully!");
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter the name of the movie you want to remove: ");
                    name =sc.nextLine();
                    theater.removeMovie(name);
                    break;
                case 3:
                    System.out.println("Enter the name of the movie you want to view: ");
                    name = sc.nextLine();
                    theater.viewaMovie(name);
                    break;
                case 4:
                    theater.viewallMovies();
                    break;
                case 5:
                    theater.addShowTime();
                    break;
                case 6:
                    theater.removeShowTime();
                    break;
                case 7:
                    theater.viewShowtime();
                    break;
                case 8:
                    theater.viewallShowtime();
                    break;
                case 9:
                    theater.buyTicket();
                    break;
                case 10:
                    theater.showSeats();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;





            }

        }while (choice != 0);


    }
    public static int FetchAPIgetMovieID(Movie movie) throws IOException {

        String encodedTitle = null;
        String line;
        StringBuffer response = new StringBuffer();
        try {
            encodedTitle = URLEncoder.encode(movie.getTitle(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        URL url = null;
        try {
            url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + API +
                    "&language=en-US&page=1&include_adult=false&query=" + encodedTitle);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        connection.setRequestProperty("Accept", "application/json");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();
            JSONObject list = new JSONObject(response.toString());
            JSONArray movies = list.getJSONArray("results");
            JSONObject movieob = movies.getJSONObject(0);
            int movie_id = movieob.getInt("id");
            return movie_id;
        }else{
            throw new RemoteException("Fail to access the API");
        }
    }
    public static Movie GetValue() throws IOException, InterruptedException {
        System.out.print("Enter the movie title: ");
        String title = sc.nextLine();
        System.out.print("Enter the director: ");

        String director = sc.nextLine();
        System.out.print("Enter the duration: ");
        int duration = sc.nextInt();
        Movie movie = new Movie(title,director,duration);
        int movie_id = FetchAPIgetMovieID(movie);
        String url = String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s&language=en-US", movie_id, API);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject info = new JSONObject(response.body());
        movie.setReleaseyear(info.getString("release_date"));
        movie.setRate(info.getDouble("vote_average"));
        movie.setNumberOfRatings(info.getInt("vote_count"));
        movie.setOverview(info.getString("overview"));
        StringJoiner joiner = new StringJoiner(", ");
        JSONArray genres = info.getJSONArray("genres");

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < genres.length(); i++) {
            String genreName = genres.getJSONObject(i).getString("name");
            stringJoiner.add(genreName);
        }
        String genresList = stringJoiner.toString();
        movie.setGenre("[ "+genresList+" ]");
        return movie;

    }

}
