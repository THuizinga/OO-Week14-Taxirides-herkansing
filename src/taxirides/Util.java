package taxirides;

/**
 *
 * @author Tiko Huizinga - s4460898
 * @author Conny Blach - s4329872
 *
 */
import java.util.Random;

public class Util {
  private static Random generator = new Random();

  /**
   * Generates a random number between from and to
   * 
   * @param from lower limit
   * @param to   upper limit
   * @return random number between from and to
   */
  public static int getRandomNumber(int from, int to) {
    if (from == to) {
      return from;
    }
    else {
      return from + generator.nextInt(to - from);
    }
  }
}