package taxirides;

/**
 *
 * @author Tiko Huizinga - s4460898
 * @author Conny Blach - s4329872
 *
 */
public class Taxirides {

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        while (!sim.ended()) {
            sim.step();
        }
        sim.showStatistics();
    }

}
