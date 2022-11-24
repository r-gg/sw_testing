package at.ac.tuwien.inso.swtesten.sample2.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikipediaSearchResultPage extends PageObject {

	@FindBy(xpath = "//h1[@id='firstHeading']")
	private WebElement heading;

	public WikipediaSearchResultPage(WebDriver driver) {
		super(driver);
	}

	public String heading() {
		return heading.getText();
	}
}
