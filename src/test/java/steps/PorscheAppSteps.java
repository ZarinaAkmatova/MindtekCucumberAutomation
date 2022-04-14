package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.PorscheAppPage;
import utilities.ConfigReader;
import utilities.Driver;

public class PorscheAppSteps {
    WebDriver driver= Driver.getDriver();
    PorscheAppPage porscheAppPage=new PorscheAppPage();

    @Given("user navigates to Porsche app")
    public void user_navigates_to_Porsche_app() {
      driver.get(ConfigReader.getProperty("PorscheAppURL"));

    }

    @When("user stores the price and selects the model {int} Cayman")
    public void user_stores_the_price_and_selects_the_model_Cayman(Integer int1) {
        porscheAppPage.porscheSelect.sendKeys(Keys.ENTER);
        porscheAppPage.priceTagForPorsche.getText();


    }
    @Then("user validates Base price is matched with listed price")
    public void user_validates_Base_price_is_matched_with_listed_price() {
        String actual=porscheAppPage.priceTagForPorsche.getText();
        String expected="From $ 60,500*";
        Assert.assertEquals(expected,actual);


        

    }


}
