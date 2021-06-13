package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.DriverUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class UIActions {
    // Global variable
    private static WebDriver driver;
    private static WebDriverWait waits;

    public UIActions() {
        driver = DriverUtil.driver();
        waits = new WebDriverWait(driver, 30);
    }

    //region BROWSER RELATED METHODS
    public void gotoSite(String url) {
        driver.get(url);
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public void fullScreen() {
        driver.manage().window().fullscreen();
    }

    public void setResolutions(int width, int height) {
        Dimension size = new Dimension(width, height);
        driver.manage().window().setSize(size);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void goFoward() {
        driver.navigate().forward();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public String title() {
        return driver.getTitle();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    //endregion


    //region ELEMENT RELATED METHODS
    public void click(By locator) {
        WebElement element = waits.until(ExpectedConditions.elementToBeClickable(locator));
        highlight(element);
        element.click();
    }

    public void click(String text) {
        By locator = xpath("//*[text()='" + text +"']");
        List<WebElement> allElems = findAllElements(locator);
        if(allElems.size() > 0) {
            for(WebElement element : allElems) {
                if(element.getText().equalsIgnoreCase(text)){
                    highlight(element);
                    element.click();
                    break;
                }
            }
        }
    }

    public void doubleClick(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
        actions.perform();
    }

    public void rightClick(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.contextClick(element);
        actions.perform();
    }

    public void dragAndDrop(By form, By target) {
        WebElement fromElem = findElement(form);
        highlight(fromElem);
        WebElement targetElem = findElement(target);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(fromElem, targetElem);
        actions.perform();
    }

    public void selectOptionsWithText(By locator, String optionText) {
        WebElement element = findElement(locator);
        highlight(element);
        Select dropdown = new Select(element);
        for(WebElement opt : dropdown.getOptions() ) {
            if(opt.getText().equals(optionText)) {
                highlight(opt);
                opt.click();
                break;
            }
        }
    }

    public void selectOptionsWithValue(By locator, String value) {
        WebElement element = findElement(locator);
        highlight(element);
        Select dropdown = new Select(element);
        for(WebElement opt : dropdown.getOptions() ){
            String extractedAttrValue = opt.getAttribute("value");
            if(extractedAttrValue.equals(value)) {
                highlight(opt);
                opt.click();
                break;
            }
        }
    }

    public WebElement moveElementToDisplay(By locator) {
        WebElement element = findElement(locator);
        highlight(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return  element;
    }

    public WebElement moveElementToDisplay(WebElement element) {
        highlight(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return  element;
    }

    public void hover(By locator) {
        WebElement element = findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public WebElement findElement(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public void highlight(By locator) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    public void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
    }

    public void write(By locator, String text) {
        try {
            WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
            highlight(input);
            input.sendKeys(text);
        }catch (Exception e) {
            System.out.println("====CLICK ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl() );
            System.out.println("Element :  " + locator.toString() );
            System.out.println("=======================================");
        }
    }

    public void clearThenWrite(By locator, String text) {
        WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear();
        input.sendKeys(text);
    }

    public void clear(By locator) {
        WebElement input = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear();
    }

    public String getText(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public boolean elementIsVisible(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        highlight(element);
        return element.isDisplayed();  // true, false
    }

    public boolean elementIsPresent(By locator) {
        try {
            WebElement element = waits.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        }catch (TimeoutException te) {
            return false;
        }
    }

    public boolean elementIsEnabled(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isEnabled();
    }

    public boolean elementIsSelected(By locator) {
        WebElement element = waits.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.isSelected();
    }
    //endregion


    //region MULTIPLE ELEMENT RELATED METHODS
    public List<WebElement> findAllElements(By locator) {
        return waits.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean clickNthElementWithText(By locator, String text) {
        List<WebElement> allElems = waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        for(WebElement element : allElems) {
            if(element.getText().equals(text)) {
                moveElementToDisplay(element);
                highlight(element);
                element.click();
                return true;
            }
        }
        // if code execution reaches here
        return false;
    }

    public List<String> getAllTexts(By locator) {
        List<WebElement> allElems = waits.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        List<String> allTexts = new ArrayList<>();  // empty list of string with size 0

        if(allElems.size() > 0) {
            for(WebElement element : allElems) {
                String currentText = element.getText();
                allTexts.add(currentText);
            }
        }
        return  allTexts;
    }
    //endregion


    //region TIME & WAITS RELATED METHODS
    public void waitFor(int second) {
        try {
            Thread.sleep(second * 1000);
        }
        catch (InterruptedException ie) {
            System.out.println("====WAIT ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl() );
            System.out.println("Browser has failed to wait for specified time");
            System.out.println("=======================================");
        }
    }

    public void waitForMilli(int millisecond) {
        try {
            Thread.sleep(millisecond);
        }
        catch (InterruptedException ie) {
            System.out.println("====WAIT ERROR========================");
            System.out.println("Where   :  " + driver.getCurrentUrl() );
            System.out.println("Browser has failed to wait for specified time");
            System.out.println("=======================================");
        }
    }

    public void waitUntilElementIsInvisible(By locator) {
        waits.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    //endregion


    //region ELEMENT LOCATING STRATEGY
    public By css(String expression) {
        return By.cssSelector(expression);
    }

    public By xpath(String expression) {
        return By.xpath(expression);
    }

    public By id(String value) {
        return By.id(value);
    }

    public By name(String value) {
        return By.name(value);
    }

    public By classAttr(String value) {
        return By.className(value);
    }

    public By link(String text) {
        return By.linkText(text);
    }

    public By linkContains(String text) {
        return By.partialLinkText(text);
    }

    public By tag(String tagname) {
        return By.tagName(tagname);
    }
    //endregion
}
