package DuckDuckGo.StepDefinitions;

import DuckDuckGo.Pages.SearchPage;
import DuckDuckGo.Utils.ConfigurationReader;
import DuckDuckGo.Utils.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SearchPageStepDefinitions {


    SearchPage searchPage = new SearchPage();

    @Given("User is on the DuckDuckGo search page")
    public void user_is_on_the_duck_duck_go_search_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
    @When("User performs a search for {string}")
    public void user_performs_a_search_for(String text) {
        searchPage.typeInto(text,searchPage.searchBox);
        searchPage.searchBox.sendKeys(Keys.ENTER);
    }
    @Then("Navigate to {string} section")
    public void navigateToSection(String sectionName) {
        searchPage.getWebElementOfDuckBarSection(sectionName).click();
    }
    @Then("List all the URL of images without DuckDuckGo url part")
    public void list_all_the_url_of_images_without_duck_duck_go_url_part() {
        for (WebElement images:searchPage.imageTiles) {
            String imageTitle = images.getAttribute("alt");
            String imageURL = images.getAttribute("src");
            System.out.println("Title: " +imageTitle+"\nURL: " + imageURL);
        }
    }
}
