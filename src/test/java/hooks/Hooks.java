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
import utils.config; // Assuming you have a config utility

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        config.loadProperties(); // Ensure your config is loaded

        // Get browser from config, provide a default if null or empty
        String browser = config.get("browser");
        if (browser == null || browser.trim().isEmpty()) {
            System.out.println("Browser property not found or is empty in config. Defaulting to 'chrome'.");
            browser = "chrome"; // Set a default browser
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
            // Add Firefox specific options if needed for private mode or password handling
            driver = new FirefoxDriver(); 
        } else if (browser.equalsIgnoreCase("edge")) {
            // Add Edge specific options if needed
            driver = new EdgeDriver(); 
        } else {
            // Fallback for an unrecognized browser value
            System.out.println("Unrecognized browser specified in config: '" + browser + "'. Defaulting to Chrome.");
            driver = new ChromeDriver(options); 
        }

        driver.manage().window().maximize();
        // You might want to add implicit waits or page load timeouts here
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
