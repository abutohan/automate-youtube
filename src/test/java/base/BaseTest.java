package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.YouTubeHomePage;
import utils.ReadJSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.Constants.BASE_URL;
import static utils.Constants.CONFIG_PROPERTIES;

public class BaseTest {

    private WebDriver driver;
    protected YouTubeHomePage homePage;

    @BeforeClass
    public void setUp() throws IOException {
        System.out.println("Opening Browser");
        ChromeOptions chromeOptions = getChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing Browser");
        driver.quit();
    }

    @BeforeMethod
    public void homePage() {
        driver.get(BASE_URL);
        homePage = new YouTubeHomePage(driver);
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_PROPERTIES)) {
            properties.load(input);
        }
        return properties;
    }

    protected Object[][] getTestDataFromJson(String propertyKey) throws IOException {
        Properties properties = loadProperties();
        String filePath = properties.getProperty(propertyKey);
        if (filePath == null) {
            throw new IllegalArgumentException("Property not found: " + propertyKey);
        }
        ReadJSON jsonFilePath = new ReadJSON(filePath);
        return jsonFilePath.getData();
    }

    private ChromeOptions getChromeOptions() throws IOException {
        Properties properties = loadProperties();
        String browserProfile = properties.getProperty("browser.profile");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(String.format("user-data-dir=%s", browserProfile));
        chromeOptions.addArguments("--profile-directory=Default");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        if (properties.getProperty("browser.headless").equals("true")) chromeOptions.addArguments("--headless");
        return chromeOptions;
    }
}