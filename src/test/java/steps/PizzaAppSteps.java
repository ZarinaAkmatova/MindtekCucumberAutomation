package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import javax.sql.rowset.BaseRowSet;
import java.util.Map;

public class PizzaAppSteps {
    WebDriver driver = Driver.getDriver();
    Map<String, Object> data;
    PizzaAppPage pizzaAppPage = new PizzaAppPage();
    String cost;

    @Given("user navigates to pizza application")
    public void user_navigates_to_pizza_application() {
        driver.get(ConfigReader.getProperty("PizzaAppURL"));

    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        data = dataTable.asMap(String.class, Object.class);
        //   for(Object object: data.values()){
        //      System.out.println(object);
        BrowserUtils.selectDropdownByValue(pizzaAppPage.pizza1Box, data.get("Pizza").toString());
        BrowserUtils.selectDropdownByValue(pizzaAppPage.topping1Box, data.get("Topping 1").toString());
        BrowserUtils.selectDropdownByValue(pizzaAppPage.topping2Box, data.get("Topping 2").toString());
        pizzaAppPage.quantity.sendKeys(Keys.BACK_SPACE);
        pizzaAppPage.quantity.sendKeys(data.get("Quantity").toString());
        pizzaAppPage.name.sendKeys(data.get("Name").toString());
        pizzaAppPage.email.sendKeys(data.get("Email").toString());
        pizzaAppPage.phone.sendKeys(data.get("Phone").toString());

        if (data.get("Payment Type").toString().equalsIgnoreCase("Cash on Pickup")) {
            pizzaAppPage.cashPayment.click();
        } else if (data.get("Payment Type").toString().equalsIgnoreCase("Credit Card")) {
            pizzaAppPage.creditCard.click();
        }

        cost = pizzaAppPage.cost.getAttribute("value");
        pizzaAppPage.placeOrderButton.click();

    }

    @Then("user validates that order is created with success message {string} {string}")
    public void user_validates_that_order_is_created_with_success_message(String success, String pizza) {
        String expectedSuccessMessage = success + cost + pizza;
        String actualSuccessMessage = pizzaAppPage.messageWindow.getText();

        Assert.assertEquals(expectedSuccessMessage,actualSuccessMessage);
    }

}
