package hooks;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	public static WebDriver driver;

	@Before
	public void setUp() {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("user-data-dir=/tmp/temp-profile-" + System.currentTimeMillis());
	}

	@Before(order = 0)
	public void launchURL() {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}

	@After
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
