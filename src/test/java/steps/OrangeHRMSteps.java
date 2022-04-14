package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMMyTimesheetPage;
import pages.OrangeHRMPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import java.util.List;
import java.util.Map;

public class OrangeHRMSteps {
    WebDriver driver = Driver.getDriver();
    OrangeHRMPage orangeHRMPage=new OrangeHRMPage();
    OrangeHRMHomePage orangeHRMHomePage=new OrangeHRMHomePage();
    OrangeHRMMyTimesheetPage orangeHRMMyTimesheetPage=new OrangeHRMMyTimesheetPage();

    @Given("user send username {string} and password {string}")
    public void user_send_username_and_password(String username, String password) {
        driver.get(ConfigReader.getProperty("OrangeHRMURL"));
        orangeHRMPage.username.sendKeys(username);
        orangeHRMPage.password.sendKeys(password);
        orangeHRMPage.loginButton.click();


    }

    @When("user navigates to {string} page")
    public void user_navigates_to_page(String string) throws InterruptedException {
        BrowserUtils.hoverOver(orangeHRMHomePage.timeBox);
        Thread.sleep(3000);
        BrowserUtils.hoverOver(orangeHRMHomePage.timesheetsBox);
        orangeHRMHomePage.myTimesheets.click();


    }

    @When("user chooses and clicks one of the {string} weeks from dropdown")
    public void user_chooses_and_clicks_one_of_the_weeks_from_dropdown(String string) {
        BrowserUtils.selectDropdownByValue(orangeHRMMyTimesheetPage.addTimeSheet, "1");

    }

    @Then("user validates the desired {string} week is selected")
    public void user_validates_the_desired_week_is_selected(String expectedData) {
        Assert.assertTrue(orangeHRMMyTimesheetPage.date1.isSelected());
        String actualTimesheet=orangeHRMMyTimesheetPage.date1.getText();

    }

    @And("user adds a row to the week")
    public void userAddsARowToTheWeek(DataTable dataTable) {
        List<Map<String,Object>> data=dataTable.asMaps(String.class, Object.class);
        orangeHRMMyTimesheetPage.editButton.click();
        orangeHRMMyTimesheetPage.addRowButton.click();
        orangeHRMMyTimesheetPage.projectNameBox.sendKeys("Apache Software Foundation - ASF - Phase 1"+ Keys.ENTER);
        BrowserUtils.selectDropdownByValue(orangeHRMMyTimesheetPage.activityNameDropdown,"49");
        orangeHRMMyTimesheetPage.saveButton.click();
        orangeHRMMyTimesheetPage.submitButton.click();
    }

    @Then("user validates the row is added")
    public void userValidatesTheRowIsAdded() {
        int numberOfRows= orangeHRMMyTimesheetPage.numberOfRows.size();
    }
}
