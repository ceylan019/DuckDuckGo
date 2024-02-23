package DuckDuckGo.Pages;

import DuckDuckGo.Utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.PageFactoryFinder;

public class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    /**
     * This is a common method that helps to type into any input field
     * @param text - the text that needs to be written into the field
     * @param webElement - the webelement of input field
     */
    public void typeInto(String text, WebElement webElement){
        webElement.clear();
        webElement.sendKeys(text);
    }
}
