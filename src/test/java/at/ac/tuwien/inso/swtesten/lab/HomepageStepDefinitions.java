package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;

public class HomepageStepDefinitions extends BugstoreStepDefinitions{

	private BugstoreHomePage bugstoreHomePage;

	public HomepageStepDefinitions(){
		When("I search for {string}", (String item) -> {
			bugstoreHomePage = getHomePage().searchForItem(item);
		});

		Then("I expect {string} item to be found", (String found) -> {
			bugstoreHomePage.assertItemsFound(found);
		});
	}
}
