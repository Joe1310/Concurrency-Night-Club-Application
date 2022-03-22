/**
 * You do not need to modify this class for submission, but may modify it 
 * to implement and test your solution.
 *
 * When marking your solution, we will use a new version of this file thus any
 * changes you make will not be marked.
*/
public class Application {
    public static void main(String[] args) throws InterruptedException {
        String name = "Fiction";
        int capacity = 20;
        Manager manager = new Manager("John Doe");
        NightClub fiction = new NightClub(name, capacity, manager);
        fiction.start();
        Thread.sleep(12000);
        fiction.end();
    }
}
