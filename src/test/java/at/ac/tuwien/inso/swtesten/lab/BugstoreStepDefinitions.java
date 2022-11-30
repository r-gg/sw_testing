package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.context.Context;
import at.ac.tuwien.inso.swtesten.lab.context.ScenarioContext;
import at.ac.tuwien.inso.swtesten.lab.pages.AccountInfoPage;
import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.lab.pages.GroupSelectionPage;
import at.ac.tuwien.inso.swtesten.lab.pages.LoginPage;
import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import io.cucumber.java8.En;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BugstoreStepDefinitions implements En {

	private WebDriver driver;

	private ScenarioContext scenarioContext;

	private GroupSelectionPage groupSelectionPage;

	private BugstoreHomePage bugstoreHomePage;

	private AccountInfoPage accountInfoPage;

	private LoginPage loginPage;

	public BugstoreStepDefinitions() {


		Before(() -> {
			driver = SeleniumWebDriver.getDriver();
			scenarioContext = new ScenarioContext();
		});

		AfterStep(scenario -> {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
			}
		});

		After(SeleniumWebDriver::closeDriver);

		Given("I login with valid credentials", () -> {
			groupSelectionPage = PageFactory.initElements(driver, GroupSelectionPage.class);
			groupSelectionPage.visit();
			bugstoreHomePage = groupSelectionPage.selectGroupAndConfirm(15);
			loginPage = bugstoreHomePage.clickLogin();
			loginPage.enterCredentials("user15.b@mail.com", "T2022-x274");
			bugstoreHomePage = loginPage.successfulLogin();
		});

		When("I navigate to my profile", () -> {
			accountInfoPage = bugstoreHomePage.navigateToAccountInfo();
			String url = driver.getCurrentUrl();
		});

		When("^I change my address to \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$",
				(String street, String zip, String city, String country) -> {
					accountInfoPage.changeAddress(street, zip, city, country);
					scenarioContext.setContext(Context.STREET, street);
					scenarioContext.setContext(Context.ZIP, zip);
					scenarioContext.setContext(Context.CITY, city);
					scenarioContext.setContext(Context.COUNTRY, country);
				});
		When("I submit my changes", () -> {
			accountInfoPage.submitChanges();
		});

		Then("A confirmation notification is shown", () -> {
			accountInfoPage.assertConfirmationNotificationShown();
		});
		Then("I can see my new address on my profile", () -> {
			accountInfoPage.assertAddressShown(
					scenarioContext.getContext(Context.STREET),
					scenarioContext.getContext(Context.ZIP),
					scenarioContext.getContext(Context.CITY),
					scenarioContext.getContext(Context.COUNTRY));
		});


	}


}
