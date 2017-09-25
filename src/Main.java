import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by OooOoOn on 2017-09-02.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("LOGIN SUCCESSFUL...");


        try {

            Thread.sleep(1000);
            System.out.println("Stand by...");
            Thread.sleep(1000);
            System.out.println("...");
            Thread.sleep(1000);

        } catch (InterruptedException ie) {

        }

        mainMenu();


    }

    private static void mainMenu() {

         //some variables
         String title;
         int numberOfDays;

        //set the date
        Date date = Calendar.getInstance().getTime();

        //declare calendar and set current date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //create administrator
        Admin administrator = new Admin();

        //Create database
        Database db = new Database();

        //boolean that breaks while loop to quit main menu
        boolean runProgram = true;

        //create scanner
        Scanner scanner = new Scanner(System.in);

        //test customer
        LinkedList<Movie> rentals = new LinkedList<>();
        Customer c = new Customer("jon", 0, rentals, 150);
        db.setCustomers(c);

        while (runProgram) {
            System.out.println("\nMENU OPTIONS");
            System.out.println("[1] Add Customer");
            System.out.println("[2] Delete Customer");
            System.out.println("[3] Edit Customer");
            System.out.println("[4] Add Movie");
            System.out.println("[5] Edit Movie");
            System.out.println("[6] Delete Movie");
            System.out.println("[7] Next day");
            System.out.println("[8] Inventory");
            System.out.println("[9] Customer rent");
            System.out.println("[10] Customer return");
            System.out.println("[11] Exit program");

            try {
                System.out.print("\nENTER YOUR CHOICE: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        //Adds new customer to database
                        db.setCustomers(administrator.addCustomer(db));
                        break;
                    case 2:
                        //Prints confirmation of customer deleting process
                        System.out.println(administrator.deleteCustomer(db));
                        break;
                    case 3:
                        //Prints confirmation of customer editing process
                        System.out.println(administrator.editCustomer(db));
                        break;
                    case 4:
                        //Adds movie to database
                        db.setMovies(administrator.addMovie(db));
                        break;
                    case 5:
                        //edits movie in database
                        administrator.editMovie(db);
                        break;
                    case 6:
                        //delete movie in database
                        System.out.println(administrator.deleteMovie(db));
                        break;
                    case 7:
                        //move to the next date.
                        System.out.println(administrator.nextDay(db, cal));
                        break;
                    case 8:
                        //inventory.
                        administrator.overview(db);
                        break;
                    case 9:
                        //rent a movie - customer test data
                        if(db.getMovies().size() == 0){
                            System.out.println("ERROR: No movies in database.");
                        }
                        else {
                            System.out.println("\n<<RENT MOVIE>>\n");
                            System.out.println("Current movies in database:");
                            for(Movie m : db.getMovies()){
                                System.out.println("\"" + m.getTitle() + "\"");
                            }
                            System.out.println("\nWhich movie would you like to rent? ");
                            System.out.print("Title: ");
                            title = scanner.nextLine();
                            System.out.println("For how many days? ");
                            System.out.print("Days: ");
                            numberOfDays = Integer.parseInt(scanner.nextLine());
                            System.out.println(administrator.rentMovie(c, title, numberOfDays, db));
                        }
                        break;
                    case 10:
                        //return a movie - customer test data
                        System.out.print("Which movie would you like to return? ");
                        title = scanner.nextLine();
                        System.out.println(administrator.returnMovie(c, title, db));
                        break;
                    case 11:
                        //the end
                        runProgram = false;
                        break;
                    default:
                        System.out.println("You need to enter a number between 1 - 7.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("You need to enter a number between 1 - 7.");
            }

        }

    }

}
