package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchActions extends UIInteractions {
	@FindBy(id = "searchString")
	private WebElement search;
	@FindBy(id = "submitSearchButton")
	private WebElement searchButton;

	@Step("Search by keyword")
	public void byKeyword(String keyword) {
		search.sendKeys(keyword);
		searchButton.click();
	}
}
