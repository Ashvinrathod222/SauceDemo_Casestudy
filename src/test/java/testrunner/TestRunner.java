package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resourcesone/demo.feature", glue = { "StepDefination", "hooks" }, plugin = {
		"pretty", "html:target/cucumber-reports.html" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
