package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class Theater {
    public static Scanner sc = new Scanner(System.in);
    ArrayList<Movie> movies = new ArrayList();
    ArrayList<ShowTime> showTimes = new ArrayList();
    ArrayList <ShowTime> availShowtimes = new ArrayList<>();
    Seat[][] seats = new Seat[10][10];

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(String name) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()){
            Movie movie = iterator.next();
            if(movie.getTitle().equals(name)){
                iterator.remove();
                System.out.println("Movie removed successfully!");
                break;
            }else{
                System.out.println("Movie not found in the list!");
            }
        }
    }
    public void viewallMovies(){
        if (movies == null){
            System.out.println("No movie in the list");
        }
        for (Movie movie: movies){
            movie.views();
            System.out.println("");
        }
    }
    public void viewaMovie(String name){
        for (Movie movie : movies){
            if(movie.getTitle().equals(name)){
                movie.views();
                break;
            }else{
                System.out.println("Movie not found in the list!");
            }
        }
    }
    public void addShowTime(){
        System.out.print("Enter the title of the movie: ");

        String name = sc.nextLine();
        String time,date;

        double ticketprice;
        for (Movie movie : movies){
            if(movie.getTitle().equals(name)){
                System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                time = sc.nextLine();
                System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                date = sc.nextLine();
                System.out.print("Enter the ticket price for the showtime: ");
                ticketprice = sc.nextDouble();
                ShowTime showTime = new ShowTime(time,date,ticketprice,name);
                showTimes.add(showTime);
                System.out.println("Showtime added successfully!");
                break;
            }else{
                System.out.println("Movie not found in the list!");
            }
        }
    }
    public void removeShowTime(){
        if (showTimes == null){
            System.out.println("No showtime in the list");
        }
        System.out.print("Enter the name of movie of showtime you want to remove: ");
        String name = sc.nextLine();
        System.out.print("Enter the time of showtime you want to remove: ");
        String time = sc.nextLine();
        System.out.print("Enter the date of showtime you want to remove: ");
        String date = sc.nextLine();
        System.out.print("Enter the ticket price of the showtime you want to remove: ");
        double ticketprice = sc.nextDouble();
        for (ShowTime showTime: showTimes){
            if(showTime.getName().equals(name) && showTime.getTime().equals(time) && showTime.getDate().equals(date) && (showTime.getPrice() == ticketprice)){
                showTimes.remove(showTime);
                System.out.println("Showtime has been removed successfully");
                System.out.println();
                break;
            }else{
                System.out.println("Showtime not found in the list");
            }
        }
    }
    public void viewShowtime(){
        if (showTimes == null) {
            System.out.println("No showtimes in the list");
        }
        System.out.print("Enter the name of movie of the show you want to see: ");
        String name = sc.nextLine();
        for (ShowTime showtime : showTimes){
            if (showtime.getName().equals(name)){
                showtime.view();
                break;
            }else{
                System.out.println("Showtime not found in the list");
            }
        }
    }
    public void viewallShowtime(){
        if (showTimes == null) {
            System.out.println("No showtimes in the list");
        }
        for (ShowTime showtime : showTimes){
            showtime.view();
            System.out.print("");

        }

    }
    public void buyTicket() {

        System.out.print("Enter the movie title: ");
        String title = sc.nextLine();


        for (ShowTime showTime : showTimes) {
            if (showTime.getName().equals(title)) {
                availShowtimes.add(showTime);
            }
        }

        if (availShowtimes.isEmpty()) {
            System.out.println("There are no showtimes available for " + title);
            return;
        }

        System.out.println("There are some available showtimes for " + title);
        for (int i = 0; i < availShowtimes.size(); i++) {
            ShowTime showTime = availShowtimes.get(i);
            System.out.printf("%d. Time: %s, Date: %s%n", (i + 1), showTime.getTime(), showTime.getDate());
        }

        System.out.print("Enter the number of showtime you want to select: ");
        int select = 0;
        try {
            select = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine(); // Consume the invalid input
            return;
        }

        if (select < 1 || select > availShowtimes.size()) {
            System.out.println("Invalid showtime selection.");
            return;
        }


        int row = 0;
        int column = 0;
        try {
            System.out.print("Enter the row of Seat: ");
            row = sc.nextInt();
            System.out.print("Enter the column of Seat: ");
            column = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid row and column numbers.");
            sc.nextLine(); // Consume the invalid input
            return;
        }

        Seat seat = new Seat(row, column);
        availShowtimes.get(select - 1).addSeat(seat);
        System.out.println("Ticket bought!");


    }
    public void showSeats(){
        System.out.print("Enter the title of movie you want to check: ");
        String title = sc.nextLine();
        System.out.print("Enter the showtime (time): ");
        String time = sc.nextLine();
        System.out.print("Enter the showtime (date): ");
        String date = sc.nextLine();
        for (ShowTime showTime : availShowtimes){
            if (showTime.getName().equals(title) ||showTime.getDate().equals(date)||showTime.getTime().equals(time)){
                Seat seat1 = showTime.getSeats();
                int row = seat1.getRow();
                int column = seat1.getColumn();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (i == row - 1 && j == column - 1) {
                            System.out.print("[X] ");
                        } else {
                            System.out.print("[O] ");
                        }
                    }
                    System.out.println();
                }

            }else{
                System.out.println("Can't find the showtime.");
            }
        }
    }








}