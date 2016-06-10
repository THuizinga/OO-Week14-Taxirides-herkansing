package taxirides;

/**
 *
 * @author Tiko Huizinga - s4460898
 * @author Conny Blach - s4329872
 *
 */
import java.util.concurrent.TimeUnit;

public class Taxi implements Runnable {

    private static final long SLEEPTIME = 100;
    private final int taxiId;
    private final int maxNrOfPassengers;
    private final int transportationTime;
    private final Station station;
    private int totalNrOfPassengers = 0;
    private int nrOfRides = 0;

    public Taxi(int nr, int maxNumberOfPassengers, int transportationTime, Station station) {
        this.taxiId = nr;
        this.maxNrOfPassengers = maxNumberOfPassengers;
        this.transportationTime = transportationTime;
        this.station = station;
        System.out.println("New taxi " + nr + " created ");
    }

    /**
     * Tries to take maxNrOfPassenegers from the station. If actual number is
     * less then that number is taken
     */

    /**
     * Calculates the total time of this taxi by multiplying the number of rides
     * by the transportation time
     *
     * @return total time
     */
    public int calcTotalTime() {
        return transportationTime * nrOfRides;
    }

    public int getTotalNrOfPassengers() {
        return totalNrOfPassengers;
    }

    /**
     * This lets the taxi wait a bit and print that it takes no passengers this time.
     */
    private void waitABit() {
        System.out.println("Taxi " + taxiId + " takes no passengers");
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEPTIME); // if no passengers at the station, wait some time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Makes the taxi active and drive around moving passengers.
     * The taxi stays active as long as the station is open.
     * If there are currently no passengers, let the taxi wait a bit, another
     * train will come soon. If there are passengers at the station, 
     * the taxi will keep bringing them to their destination until the station
     * is empty. Every time the taxi moved passengers, it will print that.
     */
    @Override
    public void run() {
        int nrOfPassengers;
        while (!station.isClosed()) {
            if (station.getNrOfPassengersWaiting() == 0) {
                waitABit();
            }
            while ((nrOfPassengers = station.leaveStation(maxNrOfPassengers)) >0) {
                totalNrOfPassengers += nrOfPassengers;
                nrOfRides++;
                System.out.println("Taxi " + taxiId + " takes " + nrOfPassengers + " passengers");
            }

        }
    }

}
