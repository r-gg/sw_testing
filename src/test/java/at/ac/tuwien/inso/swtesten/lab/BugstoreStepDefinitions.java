package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import io.cucumber.java8.En;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BugstoreStepDefinitions implements En {

	private WebDriver driver;

	public BugstoreStepDefinitions() {

		Before(() -> {
			driver = SeleniumWebDriver.getDriver();
//			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15L));
//			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15L));
		});

		AfterStep(scenario -> {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
			}
		});

		After(SeleniumWebDriver::closeDriver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public BugstoreHomePage getHomePage() {
		return PageFactory.initElements(getDriver(), BugstoreHomePage.class);
	}
}
