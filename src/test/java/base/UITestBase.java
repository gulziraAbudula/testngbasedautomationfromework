package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;

public abstract class UITestBase {

    @BeforeMethod
    public void setUp(){
        DriverUtil.open();
    }

    @AfterMethod
    public void cleanUp(){
        DriverUtil.close();
    }
}
