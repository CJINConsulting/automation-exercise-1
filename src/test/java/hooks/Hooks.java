package hooks;

import base.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (isCiEnvironment()) {
            options.addArguments("--headless=new"); // Use "--headless" if "--headless=new" causes issues
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        context.setDriver(driver);
    }

    @After
    public void tearDown() {
        WebDriver driver = context.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean isCiEnvironment() {
        return System.getenv("CI") != null || System.getenv("GITHUB_ACTIONS") != null;
    }
}
