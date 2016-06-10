package taxirides;

/**
 *
 * @author Tiko Huizinga - s4460898
 * @author Conny Blach - s4329872
 *
 * Class that holds the number of persons arriving by train at the station and
 * waiting for a taxi*
 */
public class Station {

    private int nrOfPassengersAtStation;
    private int totalNrOfPassengers;
    private boolean isClosed;

    public Station() {
        this.nrOfPassengersAtStation = 0;
        this.totalNrOfPassengers = 0;
        this.isClosed = false;
    }

    /**
     * This will add passengers to the station. This is synchronized because
     * nrOfPassengersAtStation is also used by different threads.
     * @param nrOfPassengers 
     */
    public synchronized void enterStation(int nrOfPassengers) {
        nrOfPassengersAtStation += nrOfPassengers;
        totalNrOfPassengers += nrOfPassengers;
        System.out.println("Train with " + nrOfPassengers + " passengers has arrived");
    }

    /**
     * Ask an ammount of people to leave the station.
     * If there are less people at the station then asked, let only those people
     * who are there leave.
     * @param nrOfPassengers The ammout of people asked to leave the station
     * @return The ammount of people actually leaving the station.
     */
    public synchronized int leaveStation(int nrOfPassengers) {
        int verlaatStation = 0;
        verlaatStation = Math.min(nrOfPassengers, nrOfPassengersAtStation);
        nrOfPassengersAtStation-=verlaatStation;
        return verlaatStation;
    }

    
    public int getNrOfPassengersWaiting() {
        return nrOfPassengersAtStation;
    }

    public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public int getTotalNrOfPassengers() {
        return totalNrOfPassengers;
    }

}
