package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.util.SeleniumRemoteWebDriver;
import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
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
			driver = SeleniumRemoteWebDriver.getRemoteDriver();

		});

		AfterStep(scenario -> {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
				driver.close();
			}
		});

		After(scenario -> {
			SeleniumRemoteWebDriver.closeDriver(driver);
		});
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public BugstoreHomePage getHomePage() {
		return PageFactory.initElements(getDriver(), BugstoreHomePage.class);
	}
}
