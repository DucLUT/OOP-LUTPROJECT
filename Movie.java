package pkg;

public class Movie {
    protected String Title;
    protected String releaseyear;
    protected String director;
    protected String genre;
    protected String overview;
    protected int duration;
    protected int numberOfRatings;
    protected double rate;

    public Movie(String Title, String director, int duration){
        this.Title = Title;
        this.director = director;
        this.duration = duration;
    }
    public void setTitle(String Title){
        this.Title = Title;
    }
    public String getTitle(){
        return this.Title;
    }
    public void setReleaseyear(String releaseyear){
        this.releaseyear = releaseyear;
    }
    public String getReleaseyear() {
        return this.releaseyear;
    }
    public void setDirector(String director){
        this.director = director;
    }
    public String getDirector(){
        return this.director;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getGenre(){
        return this.genre;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public int getDuration(){
        return this.duration;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }
    public String getOverview(){
        return this.overview;
    }
    public void setNumberOfRatings(int numberOfRatings){
        this.numberOfRatings = numberOfRatings;
    }
    public int getNumberOfRatings(){
        return this.numberOfRatings;
    }
    public void setRate(double rate){
        this.rate = rate;
    }
    public double getRate(){
        return this.rate;
    }
    public void views(){
        System.out.printf("Title: %s%n",getTitle());
        System.out.printf("Director: %s%n",getDirector());
        System.out.printf("Release Year: %s%n",getReleaseyear());
        System.out.printf("Review: %s%n",getOverview());
        System.out.printf("Genre: %s%n",getGenre());
        System.out.printf("Duration: %d%n",getDuration());
        System.out.printf("Rating: %f%n",getRate());
        System.out.printf("Number of Ratings: %d%n",getNumberOfRatings());
    }

}
