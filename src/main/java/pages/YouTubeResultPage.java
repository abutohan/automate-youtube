package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class YouTubeResultPage extends BasePage {

    private final By anchorVideoTitle = By.xpath("//a[@id=\"video-title\"]");

    public YouTubeResultPage(WebDriver driver) {
        super(driver);
    }

    public YouTubeVideoPage clickVideoTitle(String vidTitle) {
        getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(anchorVideoTitle));
        List<WebElement> videoTitle = getDriver().findElements(anchorVideoTitle);
        for (WebElement title : videoTitle) {
            if (title.getText().equals(vidTitle)) {
                title.click();
                break;
            }
        }
        return new YouTubeVideoPage(getDriver());
    }
}