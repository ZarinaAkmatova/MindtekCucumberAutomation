package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PorscheAppPage {
    public PorscheAppPage(){
        WebDriver driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="//img[@alt='Porsche 718 Cayman']")
    public WebElement porscheSelect;

    @FindBy(xpath = "//div[@class='m-14-model-price']")
    public WebElement priceTagForPorsche;

}
