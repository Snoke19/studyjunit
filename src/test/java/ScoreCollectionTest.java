import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScoreCollectionTest {

  private ScoreCollection collection;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void init() {
    collection = new ScoreCollection();
  }


  @Test
  public void test() {


    collection.add(() -> 5);
    collection.add(() -> 7);

    int actualResult = collection.arithmeticMean();

    assertThat(actualResult, equalTo(6));
  }

  @Test
  public void throwsExceptionWhenAddingNull() {

    thrown.expect(IllegalArgumentException.class);

    collection.add(null);
  }


  @Test
  public void answersZeroWhenNoElementsAdded() {
    assertThat(collection.arithmeticMean(), equalTo(0));
  }
}
