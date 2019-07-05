import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {TestSuiteA.class, TestSuiteB.class})
public class MasterTestSuite {
}
