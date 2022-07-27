package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HRAppHomePage {
    public HRAppHomePage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Employee ID..']")
    public WebElement searchBox;

    @FindBy(id="department")
    public WebElement departments;

    @FindBy(xpath = "(//button[@class='btn btn-success'])[2]")
    public WebElement editEmployee1;

    @FindBy(name="firstName")
    public WebElement editFirstNameBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "(//tr)[2]//td[2]")
    public WebElement employee1Name;

}