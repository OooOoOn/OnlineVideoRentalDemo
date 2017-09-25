import java.util.LinkedList;

/**
 * Created by OooOoOn on 2017-09-02.
 */
public class Customer {

    private String name;
    private int bonusPoints;
    private LinkedList<Movie> rentedMovies;
    private int money;

    public Customer(String name, int bonusPoints, LinkedList<Movie> rentedMovies, int money){
        this.name = name;
        this.bonusPoints = bonusPoints;
        this.rentedMovies = rentedMovies;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public LinkedList<Movie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(Movie rentedMovie) {
        rentedMovies.add(rentedMovie);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
