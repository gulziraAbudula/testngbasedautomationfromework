package tests;

import base.UITestBase;
import org.testng.annotations.Test;
import utility.TestListener;

public class SmokeTest extends UITestBase {

    @Test
    public void verifyHomePageDisplayed(){
        TestListener.step().info("User is on homepage");
        TestListener.step().info("User is about to login");
        TestListener.step().info("User is logged in");
    }
}
