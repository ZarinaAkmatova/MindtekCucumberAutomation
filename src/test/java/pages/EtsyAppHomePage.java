package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class EtsyAppHomePage {
    public EtsyAppHomePage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="global-enhancements-search-query")
    public WebElement searchBox;

    @FindBy(xpath ="//span[@class='wt-display-table-cell wt-text-left-xs']")
    public WebElement mothersDayGifts;

    @FindBy(id="catnav-primary-link-10855")
    public WebElement jeweleryAccessories;

    @FindBy(id="catnav-primary-link-10923")
    public WebElement clothingShoes;

    @FindBy(id="catnav-primary-link-891")
    public WebElement homeLiving;

    @FindBy(id="catnav-primary-link-10983")
    public WebElement weddingParty;

    @FindBy(id="catnav-primary-link-11049")
    public WebElement toysEntertainment;

    @FindBy(id="catnav-primary-link-66")
    public WebElement artCollectibles;

    @FindBy(id="catnav-primary-link-562")
    public WebElement craftSupplies;

    @FindBy(id="catnav-primary-link--10")
    public WebElement giftsGiftCards;







}
