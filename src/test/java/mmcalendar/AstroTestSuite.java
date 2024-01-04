package mmcalendar;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AstroKernelTest.class,
        AstroTest.class,
        AstroBatchTest.class,
})
public class AstroTestSuite {
}
