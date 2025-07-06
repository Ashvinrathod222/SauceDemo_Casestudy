package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;
import utils.config; 

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        config.loadProperties(); 

    
        String browser = config.get("browser");
        if (browser == null || browser.trim().isEmpty()) {
            System.out.println("Browser property not found or is empty in config. Defaulting to 'chrome'.");
            browser = "chrome";
        }
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); 
        options.addArguments("--remote-allow-origins=*"); 

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false); 
        options.setExperimentalOption("prefs", prefs);

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(options); 
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver(); 
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver(); 
        } else {
            System.out.println("Unrecognized browser specified in config: '" + browser + "'. Defaulting to Chrome.");
            driver = new ChromeDriver(options); 
        }

        driver.manage().window().maximize();
        
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
