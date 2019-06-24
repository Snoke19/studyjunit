import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScoreCollectionTest {

  @Test
  public void test() {

    ScoreCollection collection = new ScoreCollection();
    collection.add(() -> 5);
    collection.add(() -> 7);

    int actualResult = collection.arithmeticMean();

    assertThat(actualResult, equalTo(6));
  }
}
