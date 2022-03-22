/**
 * The primary class for your NightClub, which will have a name, capacity and
 * a manager. People can arrive and leave your club asynchronously once it is
 * opened. Once the manager tries to close the club, the entrance should be
 * closed, then close the exit once everyone has left.
 *
 * @author Joe Henry 2035032
 */
public class NightClub {
    public String name = "default";
    public boolean isOpen = false;

    private final Manager manager;
    private final int capacity;
    private int peopleCount = 0;

    private Thread exitThread;
    private Thread entranceThread;
    private Thread managerThread;

    /**
     * Instantiates a NightClub
     *
     * @param name NightClub name.
     * @param capacity Capacity of the NightClub
     * @param manager Manager of the NightClub
     */
    public NightClub(String name, int capacity, Manager manager) {
        this.name = name;
        this.capacity = capacity;
        this.manager = manager;
        manager.acceptJob(this);
    }

    /**
     * Starts the simulation by instantiating all the threads (entrance, exit, manager) and starting the managerThread.
     */
    public void start() {
        System.out.println("We are starting club:" + name);

        managerThread = new Thread(this.manager);

        Entrance entrance = new Entrance(this);
        entranceThread = new Thread(entrance);
        Exit exit = new Exit(this);
        exitThread = new Thread(exit);

        managerThread.start();
    }


    /**
     * Ends the simulation by interrupting the manager thread.
     */
    public void end() {
        managerThread.interrupt();
        System.out.println("The simulation has ended.");
    }

    /**
     * Opens the club by starting the entrance and exit threads.
     *
     * @throws InterruptedException Throws exception if the thread is waiting, sleeping or interrupted
     */
    public void open() throws InterruptedException {
        if (!isOpen) {
            isOpen = true;
            entranceThread.start();
            exitThread.start();
        } else {
            System.out.println("The club is already open!");
        }
    }

    /**
     * Closes the club by first closing the entrance thread, then closing the exit thread once everyone has left.
     *
     * @throws InterruptedException Throws exception if the thread is waiting, sleeping or interrupted
     */
    public void close() throws InterruptedException {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing the entrance.");
            entranceThread.interrupt();
            while (getPeopleCount() > 0) {
                leave();
                System.out.println("People are leaving:" + getPeopleCount());
            }
            System.out.println("Everyone has left the club.");
            System.out.println("Closing the exit.");
            exitThread.interrupt();
        } else {
            System.out.println("The club is already closed!");
        }
    }

    /**
     * Method to get the number of people in the club.
     *
     * @return Returns number of people in the club.
     */
    public int getPeopleCount() {
        return peopleCount;
    }

    /**
     * Method to allow people to enter the club.
     *
     * @throws InterruptedException Throws exception if the thread is waiting, sleeping or interrupted
     */
    public synchronized void enter() throws InterruptedException {
        // If club is full capacity, wait for a space.
        while (peopleCount == capacity) {
            wait();
        }
        peopleCount++;
        notifyAll();
    }

    /**
     * Method to allow people to leave the club.
     *
     * @throws InterruptedException Throws exception if the thread is waiting, sleeping or interrupted
     */
    public synchronized void leave() throws InterruptedException {
        // If there is no one in the club the leave function is prevented.
        while (peopleCount == 0) {
            wait();
        }
        peopleCount--;
        notifyAll();
    }
}
