package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;
    protected Logger log = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setup() {

        log.info("Loading config file");
        prop = ConfigReader.loadProperties();

        log.info("Launching browser: " + prop.getProperty("browser"));
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        log.info("Navigating to URL: " + prop.getProperty("url"));
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing browser");
        driver.quit();
    }
}
