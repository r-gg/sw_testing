package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.lab.pages.GroupSelectionPage;
import org.openqa.selenium.support.PageFactory;

public class HomepageStepDefinitions extends BugstoreStepDefinitions{

	private GroupSelectionPage groupSelectionPage;
	private BugstoreHomePage bugstoreHomePage;

	public HomepageStepDefinitions(){
		Given("I am on the homepage", () -> {
			groupSelectionPage = PageFactory.initElements(getDriver(), GroupSelectionPage.class);
			groupSelectionPage.visit();
			groupSelectionPage.selectGroup(15);
			bugstoreHomePage = groupSelectionPage.submitGroupSelection();

			bugstoreHomePage = getHomePage();
		});

		When("I search for {string}", (String item) -> {
			bugstoreHomePage = getHomePage().searchForItem(item);
		});

		Then("I expect {string} item to be found", (String found) -> {
			bugstoreHomePage.assertItemsFound(found);
		});

		Then("A tile with the title {string} and the description {string} is displayed.", (String title, String description) -> {
			bugstoreHomePage.assertItemDisplayed(title, description);
		});
	}
}
