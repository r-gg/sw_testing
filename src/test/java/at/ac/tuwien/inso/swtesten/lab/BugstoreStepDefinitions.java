package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.util.SeleniumRemoteWebDriver;
import io.cucumber.java8.En;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.PageFactory;

import java.net.URL;

public class BugstoreStepDefinitions implements En {

	private RemoteWebDriver driver;

	public BugstoreStepDefinitions() {

		Before(() -> {
			String hubUrl = "http://localhost:4444/wd/hub";
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(Browser.CHROME.browserName());

			driver = SeleniumRemoteWebDriver.getRemoteDriver();
		});

		AfterStep(scenario -> {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
				driver.close();
			}
		});

		After(SeleniumRemoteWebDriver::closeDriver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public BugstoreHomePage getHomePage() {
		return PageFactory.initElements(getDriver(), BugstoreHomePage.class);
	}
}
