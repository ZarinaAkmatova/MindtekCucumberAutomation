package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.EtsyAppHomePage;
import pages.EtsySearchResultsPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {
    WebDriver driver = Driver.getDriver(); //method
    EtsyAppHomePage etsyAppHomePage = new EtsyAppHomePage();
    EtsySearchResultsPage etsySearchResultsPage = new EtsySearchResultsPage();


    @Given("user navigates to the Etsy application")
    public void user_navigates_to_the_Etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyAppURL"));

    }

    @When("user searches for {string}")
    public void user_searches_for(String itemName) {
        etsyAppHomePage.searchBox.sendKeys(itemName + Keys.ENTER);


    }

    @When("user applies price filter over {int}")
    public void user_applies_price_filter_over(Integer price) {
        etsySearchResultsPage.allFiltersButton.click();
        etsySearchResultsPage.priceRadioButton.click();
        etsySearchResultsPage.applyButton.click();


    }

    @Then("user validates the items price is equal over {double}")
    public void user_validates_the_items_price_is_equal_over(Double price) throws InterruptedException {
        List<WebElement> prices = etsySearchResultsPage.prices;

        Thread.sleep(3000);

        for (WebElement element : prices) { // it will loop as many times as the numbers of WebElements we have
            System.out.println(element.getText());
            String priceStr = element.getText().replace(",", ""); //"1.800"-->"1800"
            double doublePrice = Double.parseDouble(priceStr);
            System.out.println(doublePrice);
            Assert.assertTrue(doublePrice >= price);

        }

    }

    @Then("user validates search result contain keyword {string} or {string}")
    public void userValidatesSearchResultContainKeywordOr(String item1, String item2) {
        List<WebElement> itemNames = etsySearchResultsPage.itemNames;

        for (int i = 0; i < itemNames.size(); i++) {
            String newItemNames = itemNames.get(i).getText();// looping through the List of webelement and getting text from each WebElement
            boolean condition = newItemNames.toLowerCase(Locale.ROOT).contains(item1) || newItemNames.toLowerCase(Locale.ROOT).contains(item2);
            if (condition) {

                StringBuilder str = new StringBuilder();//create an stringbulder object
                str.append(newItemNames); //each time it will add one more string from newitemnames till the list has elements

                System.out.println(str);
                Assert.assertTrue(str.toString().toLowerCase(Locale.ROOT).contains(item1) || str.toString().toLowerCase(Locale.ROOT).contains(item2));

            }
        }
    }


    @When("user clicks on {string} section")
    public void userClicksOnSection(String section) {
        if(section.equalsIgnoreCase("Mother's Day Gifts")){
            etsyAppHomePage.mothersDayGifts.click();
        }else if(section.equalsIgnoreCase("Jewelry and Accessories")) {
            etsyAppHomePage.jeweleryAccessories.click();
        }else if(section.equalsIgnoreCase("Clothing and Shoes")) {
            etsyAppHomePage.clothingShoes.click();
        }else if(section.equalsIgnoreCase("Home and Living")){
            etsyAppHomePage.homeLiving.click();
        }else if(section.equalsIgnoreCase("Wedding and Party")){
            etsyAppHomePage.weddingParty.click();
        }else if(section.equalsIgnoreCase("Toys and Entertainment")){
            etsyAppHomePage.toysEntertainment.click();
        }else if(section.equalsIgnoreCase("Art and Collectibles")){
            etsyAppHomePage.artCollectibles.click();
        }else if(section.equalsIgnoreCase("Craft Supplies and Tools")){
            etsyAppHomePage.craftSupplies.click();
        }else if(section.equalsIgnoreCase("Gifts and Gift Cards")){
            etsyAppHomePage.giftsGiftCards.click();



        }

    }

    @Then("user validates title is {string}")
    public void userValidatesTitleIs(String expectedTitle) {
        String actual=driver.getTitle();
        Assert.assertEquals(expectedTitle,actual);
    }
}

