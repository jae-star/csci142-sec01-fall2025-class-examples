package locktests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    locktests.ComboLockTest.class,
    locktests.KeyLockTest.class,
    locktests.KeylessEntryLockTest.class
})
public class AllTests {

}