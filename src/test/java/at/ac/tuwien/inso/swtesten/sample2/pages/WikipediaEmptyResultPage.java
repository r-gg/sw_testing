package at.ac.tuwien.inso.swtesten.sample2.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaEmptyResultPage extends PageObject {

	@FindBy(className = "mw-search-createlink")
	private WebElement pageNotExistElement;

	@FindBy(className = "mw-search-nonefound")
	private WebElement noResultsElement;

	public WikipediaEmptyResultPage(WebDriver driver) {
		super(driver);
	}

	public String pageNotExistsMessage(){
		return pageNotExistElement.getText();
	}

	public String noResultsMessage() {
		return noResultsElement.getText();
	}
}
