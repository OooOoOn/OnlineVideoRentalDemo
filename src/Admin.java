import java.util.*;

/**
 * Created by OooOoOn on 2017-09-02.
 */
public class Admin {

    private Scanner scanner = new Scanner(System.in);
    private String title;
    private String category;
    private boolean menuLoop;
    private String name;
    private int baseCostNewRelease = 40;
    private int baseCostRegular = 30;
    private int baseCostOld = 30;

    //declare list for customers to use
    private LinkedList<Movie> rentals = new LinkedList<>();

    public Customer addCustomer(Database db) {

        menuLoop = true;
        System.out.println("\n<<ADD CUSTOMER>>");

        while (menuLoop) {
            System.out.print("Name: ");
            name = scanner.nextLine().toLowerCase();
            if (!checkDuplicateName(name, db)) {
                menuLoop = false;
            } else {
                System.out.println("Name already taken.");
            }
        }

        System.out.print("Money: ");
        int money = Integer.parseInt(scanner.nextLine());

        Customer customer = new Customer(name, 0, rentals, money);
        System.out.println("\nCustomer successfully created\n");
        return customer;
    }

    public String deleteCustomer(Database db) {

        if (db.getCustomers().size() == 0) {
            return "ERROR: No customers in database";
        }

        System.out.println("\n<<DELETE CUSTOMER>>");
        System.out.println("\nCURRENT CUSTOMERS IN DATABASE");
        for (Customer c : db.getCustomers()) {
            System.out.println(c.getName());
        }

        System.out.println("\nSpecify customer to delete: ");
        System.out.print("Name: ");
        String customerName = scanner.nextLine().toLowerCase();
        Iterator<Customer> it = db.getCustomers().iterator();
        while (it.hasNext()) {
            if (it.next().getName().contains(customerName)) {
                it.remove();
                return ("Customer successfully deleted");
            } else {
            }
        }

        return "Customer does not exist";
    }

    public String editCustomer(Database db) {

        if (db.getCustomers().size() == 0) {
            return "ERROR: No customers in database";
        }

        int currentBonusPoints = 0;

        System.out.println("\n<<EDIT CUSTOMER>>");
        System.out.println("\nCURRENT CUSTOMERS IN DATABASE");
        for (Customer c : db.getCustomers()) {
            System.out.println(c.getName());
        }

        System.out.println("\nSpecify customer to edit: ");
        System.out.print("Name: ");
        String customerName = scanner.nextLine().toLowerCase();
        for (Customer c : db.getCustomers()) {
            if (c.getName().contains(customerName)) {
                System.out.println("Bonus points: " + c.getBonusPoints());
                System.out.println("MENU OPTIONS");
                System.out.println("[1] debit bonus points");
                System.out.println("[2] credit bonus points");
                System.out.println("[3] return to main menu");
                System.out.print("\nENTER YOUR CHOICE: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        //debit bonus points
                        System.out.print("Specify points to debit: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        currentBonusPoints = c.getBonusPoints();
                        c.setBonusPoints(currentBonusPoints -= choice);
                        System.out.println("Points successfully debited.");
                        break;
                    case 2:
                        //credit bonus points
                        System.out.print("\nSpecify points to credit: ");
                        choice = Integer.parseInt(scanner.nextLine());
                        currentBonusPoints = c.getBonusPoints();
                        c.setBonusPoints((currentBonusPoints += choice));
                        System.out.println("Points successfully credited.");
                        break;
                    case 3:
                        //return to main menu
                        return "";
                    default:
                        break;
                }
                return ("New point balance: " + c.getBonusPoints() + " points\n");
            } else {
            }
        }

        return "Customer does not exist";
    }

    public Movie addMovie(Database db) {

        System.out.println("\nADD A MOVIE");
        //User gets to enter numbers instead to minimize spelling abreviations.
        System.out.println("Categories: [1]New release, [2]Regular, [3]Old: ");

        boolean menuLoop = true;

        while (menuLoop) {

            System.out.print("Category: ");
            category = scanner.nextLine();

            if (category.equals("1")) {
                category = "New release";
                menuLoop = false;
            } else if (category.equals("2")) {
                category = "Regular";
                menuLoop = false;
            } else if (category.equals("3")) {
                category = "Old";
                menuLoop = false;
            } else {
                System.out.println("You need to enter a number between 1 - 3.");
            }
        }

        menuLoop = true;

        while (menuLoop) {
            System.out.print("Title: ");
            title = scanner.nextLine().toLowerCase();
            if (!checkDuplicateMovie(title, db)) {
                menuLoop = false;
            } else {
                System.out.println("Title already exists.");
            }
        }

        Movie movie = new Movie(category, title, false, 0);
        System.out.println("Movie successfully added");
        return movie;

    }


    public String editMovie(Database db) {


        if (db.getMovies().size() == 0) {
            System.out.println("ERROR: No movies in database");
        } else {

            boolean menuLoop = true;
            System.out.println("\n<<EDIT MOVIE>>");
            System.out.println("\nCURRENT MOVIES IN DATABASE");

            for (Movie m : db.getMovies()) {
                System.out.println("\"" + m.getTitle() + "\"");
            }

            System.out.println("\nSpecify Movie to edit: ");
            System.out.print("Title: ");
            String movieTitle = scanner.nextLine().toLowerCase();

            for (Movie m : db.getMovies()) {
                if (m.getTitle().contains(movieTitle)) {
                    System.out.println("Category: " + m.getCategory());
                    while (menuLoop) {
                        System.out.println("\nMENU OPTIONS");
                        System.out.println("[1] change category");
                        System.out.println("[2] change title");
                        System.out.println("[3] back to main menu");
                        System.out.print("\nENTER YOUR CHOICE: ");

                        try {
                            int choice = Integer.parseInt(scanner.nextLine());

                            switch (choice) {
                                case 1:
                                    //change category
                                    System.out.println("Categories: [1]New release, [2]Regular, [3]Old: ");
                                    System.out.print("New category: ");

                                    String category = scanner.nextLine();

                                    if (category.equals("1")) {
                                        category = "New release";
                                        m.setCategory(category);
                                        System.out.println("Category successfully updated");
                                    } else if (category.equals("2")) {
                                        category = "Regular";
                                        m.setCategory(category);
                                        System.out.println("Category successfully updated");
                                    } else if (category.equals("3")) {
                                        category = "Old";
                                        m.setCategory(category);
                                        System.out.println("Category successfully updated");

                                    } else {
                                        System.out.println("You need to enter a number between 1 - 3.");
                                    }
                                    break;
                                case 2:
                                    //change title
                                    System.out.print("New title: ");
                                    String title = scanner.nextLine();

                                    if (!checkDuplicateMovie(title, db)) {
                                        m.setTitle(title);
                                        System.out.println("Title successfully updated");
                                    } else {
                                        System.out.println("Title already exists.");
                                    }
                                    break;
                                case 3:
                                    //main menu
                                    return "Changes saved";
                                default:
                                    //wrong menu option selected
                                    System.out.println("You need to select a number between 1 - 3.");
                            }
                        } catch (Exception e) {
                            System.out.println("You need to enter a number.");
                        }
                    }


                } else {

                }

            }
        }

        return "\nMovie does not exist.\n";


    }

    //deletes a movie from database
    public String deleteMovie(Database db) {

        if (db.getMovies().size() == 0) {
            return "ERROR: No movies in database";
        }
        System.out.println("\n<<DELETE MOVIE>>");
        System.out.println("\nCURRENT MOVIES IN DATABASE");
        for (Movie m : db.getMovies()) {
            System.out.println(m.getTitle());
        }

        System.out.println("\nSpecify Movie to delete: ");
        System.out.print("Title: ");
        String movieTitle = scanner.nextLine().toLowerCase();
        Iterator<Movie> it = db.getMovies().iterator();
        while (it.hasNext()) {
            Movie m = it.next();
            if (m.getTitle().contains(movieTitle)) {
                if (m.isRented()) {
                    return "Unable to delete movie currently rented by customer.";
                }
                it.remove();
                return "Movie successfully deleted";
            } else {
            }
        }

        return "Movie does not exist";
    }

    //checks db for duplicate movies.
    public boolean checkDuplicateMovie(String title, Database db) {

        Iterator<Movie> it = db.getMovies().iterator();
        while (it.hasNext()) {
            if (it.next().getTitle().contains(title)) {
                return true;
            } else {
            }
        }
        return false;

    }

    //checks db for duplicate customers.
    public boolean checkDuplicateName(String name, Database db) {

        Iterator<Customer> it = db.getCustomers().iterator();
        while (it.hasNext()) {
            if (it.next().getName().contains(name)) {
                return true;
            } else {
            }
        }
        return false;

    }

    //rents a movie
    public String rentMovie(Customer c, String title, int numberOfDays, Database db) {

        int totalRentalCost = 0;
        int bonusPoints = 0;

        for (Movie m : db.getMovies()) {
            if (m.getTitle().contains(title)) {
                //movie is already rented and not late
                if (m.isRented() && m.getRentalPeriod() > 0) {
                    return "Movie is already rented. Will be available again in " + m.getRentalPeriod() + " days.";
                }
                //movie is already rented and not returned in time.
                else if (m.isRented() && m.getRentalPeriod() < 0) {
                    return "Movie is already rented and customer has not returned it in time. Check back again in a few days.";
                }
                //New release - daily cost 40 SEK.
                if (m.getCategory().contains("New release")) {
                    totalRentalCost = baseCostNewRelease * numberOfDays;
                    bonusPoints += 2;
                    totalRentalCost = payment(c, totalRentalCost, baseCostNewRelease);
                }

                //Regular - First 3 days 30 SEK + daily cost 30 SEK.
                else if (m.getCategory().contains("Regular")) {
                    for (int i = 3; i <= numberOfDays; i++) {
                        totalRentalCost += 30;
                    }
                    totalRentalCost += baseCostRegular;
                    bonusPoints += 1;
                    totalRentalCost = payment(c, totalRentalCost, baseCostRegular);
                }

                //Old - First 5 days 30 SEK + daily cost 30 SEK.
                else if (m.getCategory().contains("Old")) {
                    for (int i = 5; i < numberOfDays; i++) {
                        totalRentalCost += 30;
                    }
                    totalRentalCost += baseCostOld;
                    bonusPoints += 1;
                    totalRentalCost = payment(c, totalRentalCost, baseCostOld);
                }

                if (totalRentalCost != 0) {
                    //cancels rental since customer cannot pay.
                    return "Unable to process payment. Operation cancelled.";
                } else {
                    //adds accumulated bonus points to customer account.
                    c.setBonusPoints(bonusPoints);
                    //mark movie as rented.
                    m.setRented(true);
                    m.setRentalPeriod(numberOfDays);
                    //add movie to customer list of rented movies.
                    c.setRentedMovies(m);
                    return "Movie successfully rented. Number of days: " + numberOfDays + ".";
                }
            }

        }

        return "Movie " + "\"" + title + "\"" + " not found.";

    }

    //handles payment using money or loyalty points
    public int payment(Customer c, int totalRentalCost, int baseCost) {

        while (totalRentalCost > 0) {
            //customer chooses payment method.
            System.out.println("\nPAYMENT METHOD:");
            System.out.println("[1] Cash");
            System.out.println("[2] Bonus points");
            System.out.println("\nOutstanding amount: " + totalRentalCost + " SEK.");
            System.out.print("ENTER YOUR CHOICE: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    if (c.getMoney() > totalRentalCost) {
                        c.setMoney(c.getMoney() - totalRentalCost);
                        System.out.println("Payment processed. Remaining customer balance: " + c.getMoney() + " SEK.");
                        totalRentalCost = 0;

                    } else {
                        System.out.println("Insufficient funds. ");
                        return totalRentalCost;
                    }
                    break;
                case 2:
                    if (c.getBonusPoints() > 25) {
                        c.setBonusPoints(c.getBonusPoints() - 25);
                        totalRentalCost -= baseCost;
                        System.out.println("Points deducted. Remaining points: " + c.getBonusPoints());
                    } else {
                        System.out.println("Not enough bonus points. Need a minimum of 25 points.");
                    }
            }

        }
        return 0;
    }

    //moves the date forward to calculate late returns etc.
    public String nextDay(Database db, Calendar cal) {


        cal.add(Calendar.DATE, 1);

        //deduct one day from rented movies.
        for (Movie m : db.getMovies()) {
            if (m.isRented()) {
                m.setRentalPeriod(m.getRentalPeriod() - 1);
            }
        }

        return "Today's date is " + cal.getTime();
    }

    //gives an overview of all store customer and movies
    public void overview(Database db) {

        if (db.getMovies().size() == 0) {
            System.out.println("NO MOVIES IN DATABASE");
        } else {
            for (Movie m : db.getMovies()) {
                System.out.println("\n<<MOVIE>>");
                System.out.println("Title: \"" + m.getTitle() + "\"");
                System.out.println("Category: " + m.getCategory());
                System.out.println("Rented: " + m.isRented());
                if (m.isRented()) {
                    System.out.println("Returned to store in " + m.getRentalPeriod() + " days.");
                }
            }
        }

        if (db.getCustomers().size() == 0) {
            System.out.println("NO CUSTOMERS IN DATABASE");
        } else {
            for (Customer c : db.getCustomers()) {
                System.out.println("\n<<CUSTOMER>>");
                System.out.println("Customer name: " + c.getName());
                System.out.println("Rented Movies: ");
                for (Movie m : c.getRentedMovies()) {
                    System.out.println("\"" + m.getTitle() + "\"");
                }
            }
        }
    }

    //returns movie to store
    public String returnMovie(Customer c, String title, Database db) {

        int lateFee = 0;

        System.out.println("\n<<RETURN A MOVIE>>");

        for (Movie m : db.getMovies()) {
            if (m.getTitle().contains(title)) {
                if (m.isRented()) {

                    //reset movie parameters
                    c.getRentedMovies().remove(m);
                    m.setRentalPeriod(0);
                    m.setRented(false);

                    System.out.println("\nMovie returned: \"" + m.getTitle() + "\".");
                    if (m.getRentalPeriod() >= 0) {
                        return "Thank you for returning your movie \"" + m.getTitle() + "\" on time. Have a nice day.";
                    } else if (m.getRentalPeriod() < 0) {
                        if (m.getCategory().contains("New release")) {
                            lateFee = baseCostNewRelease * (m.getRentalPeriod() * -1);
                            System.out.println("Late charges: " + baseCostNewRelease + " SEK per late day.");
                            System.out.println("Outstanding amount: " + lateFee + " SEK.");
                        } else if (m.getCategory().contains("Regular")) {
                            lateFee = baseCostRegular * (m.getRentalPeriod() * -1);
                            System.out.println("Late charges: " + baseCostRegular + " SEK per late day.");
                            System.out.println("Outstanding amount: " + lateFee + " SEK.");
                        } else if (m.getCategory().contains("Old")) {
                            lateFee = baseCostOld * (m.getRentalPeriod() * -1);
                            System.out.println("Late charges: " + baseCostOld + " SEK per late day.");
                            System.out.println("Outstanding amount: " + lateFee + " SEK.");
                        }

                        System.out.println("Customer balance: " + c.getMoney() + " SEK.");

                        if (lateFee > c.getMoney()) {
                            return "STAFF ANNOUNCEMENT: CUSTOMER UNABLE TO PAY. RESTRAIN CUSTOMER UNTIL LAW ENFORCEMENT OFFICER ARRIVES.";
                        } else {
                            c.setMoney(c.getMoney() - lateFee);
                            return "Late fee deducted from customer balance. Have a nice day.";
                        }

                    }
                }
            }
        }

        return "Movie not in database.";
    }
}
