import org.junit.Test;

import static java.lang.Math.abs;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class NewtonTest {

  static class Newton {
    private static final double TOLERANCE = 1E-16;
    public static double squareRoot(double n) {
      double approx = n;
      while (abs(approx - n / approx) > TOLERANCE * approx)
        approx = (n / approx + approx) / 2.0;
      return approx;
    }
  }


  @Test
  public void squareRoot() {
    double result = Newton.squareRoot(250.0);
    assertThat(result * result, closeTo(250.0, Newton.TOLERANCE));
  }

}
