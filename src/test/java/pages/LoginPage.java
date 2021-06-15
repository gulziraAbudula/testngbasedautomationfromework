package pages;

import base.UIActions;
import org.openqa.selenium.By;

public class LoginPage extends UIActions {

    // -------List of element locations
    private By page_banner = xpath("//h1[text()='Log in to Trello']");
    private By username_input = id("user");
    private By password_input = id("password");
    private By login_button = id("login");
    private By error_message = xpath("(//p[@class='error-message'])[1]");

    // -------user's action on the page
    public boolean verifyPageIsDisplayed(){
        return elementIsVisible(page_banner);
    }

    public void enterCredentials(String username, String password){
        waitFor(2);
        write(username_input,username);
        waitFor(1);
        write(password_input,password);
    }

    public void clickLoginButton(){
        click(login_button);
    }

    public boolean verifyErrorMessageIsVisible(){
        waitFor(1);
        return elementIsVisible(error_message);
    }
}
