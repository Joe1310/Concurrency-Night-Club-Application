/**
 * The class which will act as the entrance for your nightclub, modelling
 * people arriving at and entering your nightclub.
 *
 * @author Joe Henry 2035032
 */
public class Entrance implements Runnable{
    private final NightClub nightClub;
    private String name = "Entrance";

    /**
     * Instantiates an Entrance for a NightClub.
     *
     * @param nightClub Name of the NightClub it is the entrance to.
     */
    public Entrance (NightClub nightClub){
        this.nightClub = nightClub;
    }

    /**
     * Instantiates an Entrance for a NightClub.
     *
     * @param nightClub Name of the NightClub it is the entrance to.
     * @param name Name of the exit.
     */
    public Entrance (NightClub nightClub, String name){
        this.nightClub = nightClub;
        this.name = name;
    }

    /**
     * Method to make people enter the club after a random amount of time.
     */
    public void run() {
        while (true) {
            try {
                double random = Math.random();
                int sleepScaler = 10;
                Thread.sleep((long) (random * sleepScaler));
                nightClub.enter();
                System.out.println(name + " arrive: " + nightClub.getPeopleCount());
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Arrival Thread");
                return;
            }
        }
    }
}
