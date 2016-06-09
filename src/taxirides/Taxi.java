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
    public void takePassengers() {
        int passengersWaiting = station.getNrOfPassengersWaiting();
        int nrOfPassengers = Math.min(passengersWaiting, maxNrOfPassengers);
        if (station.leaveStation(nrOfPassengers)) {
            totalNrOfPassengers += nrOfPassengers;
            nrOfRides++;
            System.out.println("Taxi " + taxiId + " takes " + nrOfPassengers + " passengers");
        } else {
            System.out.println("Taxi " + taxiId + " takes no passengers");
            try {
                TimeUnit.MILLISECONDS.sleep(SLEEPTIME); // if no passengers at the station, wait some time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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

    private void waitABit() {
        System.out.println("Taxi " + taxiId + " takes no passengers");
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEPTIME); // if no passengers at the station, wait some time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        int nrOfPassengers;
        while (!station.isClosed() || station.getNrOfPassengersWaiting() > 0) {
            if (station.getNrOfPassengersWaiting() == 0) {
                waitABit();
            }
            while (!station.leaveStation(nrOfPassengers = Math.min(station.getNrOfPassengersWaiting(), maxNrOfPassengers))) {
                totalNrOfPassengers += nrOfPassengers;
                nrOfRides++;
                System.out.println("Taxi " + taxiId + " takes " + nrOfPassengers + " passengers");
            }

        }
    }

}
