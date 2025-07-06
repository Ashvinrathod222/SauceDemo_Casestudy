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
        String browser = config.get("browser"); // Assuming you have a "browser" property in your config

        ChromeOptions options = new ChromeOptions();
        
        // Add argument for Incognito mode (if you still want it)
        options.addArguments("--incognito"); 
        
        // Essential argument for modern Chrome/ChromeDriver versions
        options.addArguments("--remote-allow-origins=*"); 

        // --- Preferences to disable password manager popups, including data breach warning ---
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // Disables "Do you want to save password?"
        prefs.put("profile.password_manager_enabled", false); // Disables built-in password manager
        prefs.put("profile.password_manager_leak_detection", false); // THIS IS THE KEY ONE FOR DATA BREACH WARNING
        options.setExperimentalOption("prefs", prefs);
        // -----------------------------------------------------------------------------------

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(options); // Pass the configured options here
        } else if (browser.equalsIgnoreCase("firefox")) {
            // For Firefox, you'd use FirefoxOptions and set different preferences
            // For example, to disable password save in Firefox:
            // FirefoxOptions firefoxOptions = new FirefoxOptions();
            // firefoxOptions.addPreference("signon.rememberSignons", false);
            // driver = new FirefoxDriver(firefoxOptions);
            driver = new FirefoxDriver(); // Default Firefox
        } else if (browser.equalsIgnoreCase("edge")) {
            // Similar for Edge, use EdgeOptions
            driver = new EdgeDriver(); // Default Edge
        } else {
            System.out.println("Invalid browser specified in config. Defaulting to Chrome.");
            driver = new ChromeDriver(options); // Default to Chrome with Incognito and popup disabled
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
