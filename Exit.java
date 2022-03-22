/**
 * The class which will act as the exiting for your nightclub, modelling
 * people leaving your nightclub.
 *
 * @author Joe Henry 2035032
 */
public class Exit implements Runnable{
    private final NightClub nightClub;
    private String name = "Exit";

    /**
     * Instantiates an Exit for a NightClub.
     *
     * @param nightClub Name of the NightClub it is the exit to.
     */
    public Exit (NightClub nightClub){
        this.nightClub = nightClub;
    }

    /**
     * Instantiates an Exit for a NightClub.
     *
     * @param nightClub Name of the NightClub it is the exit to.
     * @param name Name of the exit.
     */
    public Exit (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }

    /**
     * Method to make people leave the club after a random amount of time.
     */
    public void run() {
        while (true){
            try {
                double random = Math.random();
                int sleepScaler = 10;
                Thread.sleep((long) (random* sleepScaler));
                nightClub.leave();
                System.out.println(name + " depart: " + nightClub.getPeopleCount());
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Departure Thread");
                return;
            }
        }
    }
}
