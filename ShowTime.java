package pkg;

import java.util.ArrayList;

public class ShowTime {
    protected String time,date,name;
    protected double price;
    public static Seat seat;



    public void addSeat(Seat seat){
        this.seat = seat;
    }

    public Seat getSeats() {
        return seat;
    }

    public ShowTime(String time, String date, double price, String name){
        this.time = time;
        this.date = date;
        this.price = price;
        this.name = name;
    }

    public void setTime(String time){
        this.time = time;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public String getTime(){
        return this.time;
    }
    public String getDate(){
        return this.date;
    }
    public double getPrice(){
        return this.price;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void view(){
        System.out.println("Movie: "+this.name);
        System.out.println("Time: " + this.time);
        System.out.println("Date: "+ this.date);
        System.out.println("Ticket Price: " + this.price);
    }
}
