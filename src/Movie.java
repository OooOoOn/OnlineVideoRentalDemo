/**
 * Created by OooOoOn on 2017-09-02.
 */
public class Movie {

    private String category;
    private String title;
    private boolean rented;
    private int rentalPeriod;

    public Movie(String category, String title, boolean rented, int rentalPeriod){
        this.category = category;
        this.title = title;
        this.rented = rented;
        this.rentalPeriod = rentalPeriod;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public int getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

}
