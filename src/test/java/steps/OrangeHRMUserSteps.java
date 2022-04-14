package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRMHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class OrangeHRMUserSteps<Some> {
    WebDriver driver = Driver.getDriver();
    OrangeHRMHomePage orangeHRMHomePage=new OrangeHRMHomePage();

     @When("user navigates to users page")
     public void user_navigates_to_users_page() {
         driver.get(ConfigReader.getProperty("OrangeHRMURL"));
         BrowserUtils.hoverOver(orangeHRMHomePage.admin);
         BrowserUtils.hoverOver(orangeHRMHomePage.userManagement);
         BrowserUtils.hoverOver(orangeHRMHomePage.userButton);


     }

     @Then("user searches for valid user with data")
     public void user_searches_for_valid_user_with_data() {

     }
     @Then("validate user is shown")
     public void validates_user_is_shown() {

     }
     @Then("user searches for invalid user")
     public void user_searches_for_invalid_user() {

     }
     @Then("validates error {string} message")
     public void validates_error_message(String string) {

     }


 }

