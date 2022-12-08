package at.ac.tuwien.inso.swtesten.sample2;

import at.ac.tuwien.inso.swtesten.sample2.pages.WikipediaEmptyResultPage;
import at.ac.tuwien.inso.swtesten.sample2.pages.WikipediaLanguageSelectionPage;
import at.ac.tuwien.inso.swtesten.sample2.pages.WikipediaMainPage;
import at.ac.tuwien.inso.swtesten.sample2.pages.WikipediaSearchResultPage;
import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class WikipediaStepDefinitions implements En {

	private WebDriver driver;

	private WikipediaLanguageSelectionPage languageSelectionPage;
	private WikipediaMainPage mainPage;
	private WikipediaSearchResultPage searchResultPage;
	private WikipediaEmptyResultPage emptyResultPage;

	public WikipediaStepDefinitions() {

//		Before(() -> {
//			driver = SeleniumWebDriver.getDriver();
//		});
//
//		AfterStep( scenario -> {
//			if(scenario.isFailed()) {
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				scenario.attach(screenshot, "image/png", "failed_" + scenario.getName());
//			}
//		});
//
//		Given("the browser language is {string}", SeleniumWebDriver::setDefaultLocale);
//
//		Given("I am on the Wikipedia start page", () -> {
//			languageSelectionPage = PageFactory.initElements(driver, WikipediaLanguageSelectionPage.class);
//			languageSelectionPage.visit();
//		});
//
//		Given("the English version is selected", () -> {
//			mainPage = languageSelectionPage.selectLanguage();
//		});
//
//		When("I enter the search term {string}", (String searchTerm) -> {
//			searchResultPage = mainPage.searchValid(searchTerm);
//		});
//
//		When("I enter the invalid search term {string}", (String searchTerm) -> {
//			emptyResultPage = mainPage.searchInvalid(searchTerm);
//		});
//
//		Then("the result page with heading {string} is shown", (String heading) -> {
//			Assert.assertEquals(heading, searchResultPage.heading());
//		});
//
//		Then("no results are found and an error message {string} is displayed", (String heading) -> {
//			Assert.assertEquals("The page \"" + heading + "\" does not exist. You can create a draft and submit it for review.",
//					emptyResultPage.pageNotExistsMessage());
//			Assert.assertEquals("There were no results matching the query.",
//					emptyResultPage.noResultsMessage());
//		});
//
//		After(SeleniumWebDriver::closeDriver);

	}
}
