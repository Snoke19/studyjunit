import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {

  private int expected;
  private int valueOne;
  private int valueTwo;

  @Parameterized.Parameters
  public static Collection<Integer[]> getTestParameters() {
    return Arrays.asList(new Integer[][] {
            {2, 1, 1},
            {3, 2, 1},
            {4, 3, 1},
    });
  }


  public ParameterizedTest(int expected, int valueOne, int valueTwo) {
    this.expected = expected;
    this.valueOne = valueOne;
    this.valueTwo = valueTwo;
  }

}
