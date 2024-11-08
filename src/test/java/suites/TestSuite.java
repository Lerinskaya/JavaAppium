package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.ChangeAppConditionTests;
import tests.GetStartedTest;
import tests.MyListsTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
//        ArticleTests.class,
        ChangeAppConditionTests.class,
//        GetStartedTest.class,
//        MyListsTest.class
})

public class TestSuite {
}
