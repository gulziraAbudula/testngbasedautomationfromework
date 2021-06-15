package tests;

import base.UITestBase;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utility.TestListener;

//@Listeners(utility.TestListener.class)
public class SmokeTest extends UITestBase {

    @Test
    public void verifyHomePageDisplayed(){
        HomePage home = new HomePage();
        log("User is on home page");
        home.open();

        log("Verifying that homepage is displayed to the user");
        Assert.assertTrue(home.verifyPageIsDisplayed());
    }

    @Test
    public void verifyInvalidLoginWillResultInError(){
        //Test Data
        HomePage home = new HomePage();
        LoginPage login = new LoginPage();

        log("user is on homepage");
        home.open();
        home.goToLoginPage();

        log("User is on login page");
        boolean ret1 = login.verifyPageIsDisplayed();

        //Test steps
        log("User is entering the invalid credentials");
        login.enterCredentials(fakeUser(),fakePass());
        login.clickLoginButton();

        log("Verifying that error message is displayed");
        boolean ret2 = login.verifyErrorMessageIsVisible();

        //Test assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(ret1);
        softAssert.assertTrue(ret2);
        softAssert.assertAll();

    }
}
