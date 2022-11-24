package at.ac.tuwien.inso.swtesten.sample2.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaMainPage extends PageObject {

	@FindBy(id = "searchInput")
	private WebElement searchInputField;

	@FindBy(id = "searchButton")
	private WebElement searchButton;

	public WikipediaMainPage(WebDriver driver) {
		super(driver);
	}

	public WikipediaSearchResultPage searchValid(String text) {
		return search(text, WikipediaSearchResultPage.class);
	}

	public WikipediaEmptyResultPage searchInvalid(String text) {
		return search(text, WikipediaEmptyResultPage.class);
	}

	private <T extends PageObject> T search(String text, Class<T> expectedResultPage) {
		searchInputField.clear();
		searchInputField.sendKeys(text);
		searchButton.click();

		return initPage(expectedResultPage);
	}
}
