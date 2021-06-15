package base;

import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.DriverUtil;
import utility.TestListener;

public abstract class UITestBase {

    @BeforeMethod
    public void setUp(){
        DriverUtil.open();
    }

    @AfterMethod
    public void cleanUp(){
        DriverUtil.close();
    }

    public void log(String message){
        TestListener.step().info(message);
    }

    public String fakeUser(){
        return new Faker().name().username();
    }

    public String fakePass(){
        return new Faker().internet().password();
    }
}
