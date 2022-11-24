package at.ac.tuwien.inso.swtesten.sample1;

import static at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver.waitUntil;
import static org.junit.Assert.assertEquals;

import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaSeleniumHelper {
	private WebDriver driver;
	private String baseUrl;

	public void setUp() {
		driver = SeleniumWebDriver.getDriver();
		baseUrl = "http://www.wikipedia.org/";
	}

	public void setBrowserLanguage(String locale) {
		SeleniumWebDriver.setDefaultLocale(locale);
	}

	public void selectLanguage() {
		driver.get(baseUrl);

		WebElement element = driver.findElement(By.xpath("//strong[text() = 'English']"));
		waitUntil(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void search(String searchText) {
		driver.findElement(By.id("searchInput")).clear();
		driver.findElement(By.id("searchInput")).sendKeys(searchText);
		driver.findElement(By.id("searchButton")).click();
	}

	public void assertSearchResult(String heading) {
		assertEquals(heading, driver.findElement(By.xpath("//h1[@id='firstHeading']")).getText());
	}

	public void assertArticleNotExists(String heading) {
		WebElement element = driver.findElement(By.className("mw-search-createlink"));
		assertEquals("The page \"" + heading + "\" does not exist. You can create a draft and submit it for review.", element.getText());

		WebElement element2 = driver.findElement(By.className("mw-search-nonefound"));
		assertEquals("There were no results matching the query.", element2.getText());
	}

	public void shutDown() {
		SeleniumWebDriver.closeDriver();
	}

	public byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}
