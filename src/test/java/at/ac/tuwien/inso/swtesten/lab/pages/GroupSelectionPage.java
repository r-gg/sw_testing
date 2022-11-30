package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class GroupSelectionPage extends PageObject {



	@FindBy(name = "group")
	private WebElement groupSelectorField;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement submitButton;

	public GroupSelectionPage(WebDriver driver) {
		super(driver);
	}

	public void visit() {
		driver.get("https://peso.inso.tuwien.ac.at/st/v1/selectGroup");
	}

	public BugstoreHomePage selectGroupAndConfirm(int groupNr){
		// Select and submit
		Select groupSelector = new Select(groupSelectorField);
		String groupNumber = (groupNr >= 10)? ""+groupNr : "0" + groupNr;
		groupSelector.selectByValue("grp"+ groupNumber);
		submitButton.click();
		return initPage(BugstoreHomePage.class);
	}

}
