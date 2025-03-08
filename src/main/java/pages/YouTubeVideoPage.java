package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YouTubeVideoPage extends BasePage {

    private final By lblVideoDuration = By.xpath("//span[@class=\"ytp-time-duration\"]");

    public YouTubeVideoPage(WebDriver driver) {
        super(driver);
    }

    public String getVideoDuration() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(lblVideoDuration));
        WebElement videoDuration = getDriver().findElement(lblVideoDuration);
        return videoDuration.getText();
    }

    public void playVideo(int videoDuration) throws InterruptedException {
        Thread.sleep(videoDuration);
    }
}