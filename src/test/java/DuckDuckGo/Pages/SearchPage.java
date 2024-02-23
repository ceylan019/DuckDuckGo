package DuckDuckGo.Pages;

import DuckDuckGo.Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{

    @FindBy(id = "searchbox_input")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class='tile  tile--img  has-detail']//img")
    public List<WebElement> imageTiles;


    /**
     * This is a generic methods that returns web element of a section on duckbar based on section name
     * @param sectionName - The section name that you need web element of
     * @return - WebElement
     */
    public WebElement getWebElementOfDuckBarSection(String sectionName){
        return Driver.getDriver().findElement(By.xpath("//div[@id='duckbar']//a[contains(text(),'"+sectionName+"')]"));
    }
}
