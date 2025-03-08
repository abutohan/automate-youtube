package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YouTubeHomePage extends BasePage {

    @FindBy(name = "search_query")
    @CacheLookup
    private WebElement txtSearch;

    @FindBy(xpath = "//div[@id=\"center\"]//button[@title=\"Search\" and @aria-label=\"Search\"]")
    @CacheLookup
    private WebElement btnSearch;

    public YouTubeHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setTxtSearch(String keyWord) {
        getWait().until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.sendKeys(keyWord);
    }

    public YouTubeResultPage clickSearch() {
        getWait().until(ExpectedConditions.elementToBeClickable(btnSearch));
        btnSearch.click();
        return new YouTubeResultPage(getDriver());
    }
}