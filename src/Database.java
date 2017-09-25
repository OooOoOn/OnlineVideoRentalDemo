import java.util.LinkedList;
import java.util.List;

/**
 * Created by OooOoOn on 2017-09-02.
 */
public class Database {

    private LinkedList<Movie> Movies = new LinkedList<>();
    private LinkedList<Customer> CustomerList = new LinkedList<>();

    public Database(){

    }

    public void setCustomers(Customer customer) {
        CustomerList.add(customer);
    }

    public List<Customer> getCustomers() {
        return CustomerList;
    }

    public void setMovies(Movie movie) {
        Movies.add(movie);
    }

    public List<Movie> getMovies() {
        return Movies;
    }
}
