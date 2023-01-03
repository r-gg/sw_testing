package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class GroupSelectionPage extends PageObject {
	@FindBy(name = "group")
	private WebElement groupSelectorField;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;

	public GroupSelectionPage(WebDriver driver) {
		super(driver);
	}

	public void visit() {
		driver.get("https://peso.inso.tuwien.ac.at/st/v2/selectGroup");
	}

	public void selectGroup(int groupNr){
		// Select and submit
		Select groupSelector = new Select(groupSelectorField);
		String groupNumber = (groupNr >= 10)? ""+groupNr : "0" + groupNr;
		groupSelector.selectByValue("grp"+ groupNumber);
	}

	public BugstoreHomePage submitGroupSelection(){
		submitButton.click();
		return initPage(BugstoreHomePage.class);
	}

}
