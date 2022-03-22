/**
 * The manager is responsible for opening and closing the nightclub.
 *
 * @author Joe Henry 2035032
 */
public class Manager implements Runnable {
    private final String name;
    private NightClub nightClub;

    /**
     * Instantiates a Manager object.
     *
     * @param name Manager Name.
     */
    public Manager(String name) {
        this.name = name;
    }

    /**
     * Instantiates a Manager object.
     *
     * @param name Manager Name.
     * @param nightClub NightClub Managed.
     */
    public Manager(String name, NightClub nightClub) {
        this.name = name;
        this.nightClub = nightClub;
    }

    /**
     * Method to set the managers NightClub.
     *
     * @param nightClub The NightClub that the manager is now managing.
     */
    public void acceptJob(NightClub nightClub) {
        this.nightClub = nightClub;
    }

    /**
     * Method to set the managers nightclub to null (leave their job).
     */
    public void leaveJob() {
        this.nightClub = null;
    }

    /**
     * Method to make the manager open the nightclub, leave it open for x amount of time then close the nightclub.
     */
    public void run() {
        try {
            this.nightClub.open();
            int sleepScaler = 10000;
            Thread.sleep(sleepScaler);
            this.nightClub.close();
        } catch (InterruptedException e) {
            System.out.println("NightClub already open");
        }
    }
}
