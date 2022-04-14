package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class OrangeHRMMyTimesheetPage {
    public OrangeHRMMyTimesheetPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="startDates")
    public WebElement addTimeSheet;

    @FindBy(xpath = "//option[@value='1']")
    public WebElement date1;

    @FindBy(id = "btnEdit")
    public WebElement editButton;

    @FindBy(id = "btnAddRow")
    public WebElement addRowButton;

    @FindBy(id = "initialRows_0_projectName")
    public WebElement projectNameBox;

    @FindBy(id = "initialRows_0_projectActivityName")
    public WebElement activityNameDropdown;

    @FindBy(id = "submitSave")
    public WebElement saveButton;

    @FindBy(id = "btnSubmit")
    public WebElement submitButton;

    @FindBy(id="initialRows_0_projectName")
    public WebElement lineOne;

    @FindBy(id="initialRows_0_projectActivityName")
    public WebElement activityName;

    @FindBy(id="initialRows_0_0")
    public WebElement monday;

    @FindBy(id="initialRows_0_1")
    public WebElement tuesday;

    @FindBy(id="initialRows_0_2")
    public WebElement wednesday;

    @FindBy(id="initialRows_0_3")
    public WebElement thursday;

    @FindBy(id="initialRows_0_4")
    public WebElement friday;

    @FindBy(id="initialRows_0_5")
    public WebElement saturday;

    @FindBy(id="initialRows_0_6")
    public WebElement sunday;

    @FindBy(id = "timesheet_status")
    public WebElement status;

    @FindBy(tagName = "Status: Submitted")
    public WebElement statusSubmitted;

    @FindBy(xpath = "//div[@class='inner']//tr")
    public List<WebElement> numberOfRows;

}
