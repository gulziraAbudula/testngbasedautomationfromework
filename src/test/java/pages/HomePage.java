package pages;

import base.UIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends UIActions {

    private String url = "https://trello.com";
    // -------List of elements locations
    private By page_banner_text =xpath("//h1");
    private By login_icon = xpath("//a[text()='Log in']");

    // --------User's Action in the page
    public void open(){
        gotoSite(url);
    }

    public boolean verifyPageIsDisplayed(){
        WebElement banner = findElement(page_banner_text);
        return banner.isDisplayed();
    }

    public void goToLoginPage(){
        click(login_icon);
    }

}
