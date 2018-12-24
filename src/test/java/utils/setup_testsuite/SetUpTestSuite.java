package utils.setup_testsuite;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;

/**
 * User: Nhi Dinh
 * Date: 5/12/2018
 */
public class SetUpTestSuite implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites){
        for(XmlSuite suite:suites){
            XmlTest test = new XmlTest();
            test.setName("New Test Case");
        }
    }
}
