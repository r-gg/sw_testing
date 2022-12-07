package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.pages.BugstoreHomePage;
import at.ac.tuwien.inso.swtesten.lab.pages.GroupSelectionPage;
import at.ac.tuwien.inso.swtesten.lab.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;

public class AuthStepDefinitions extends BugstoreStepDefinitions {

	private GroupSelectionPage groupSelectionPage;

	private BugstoreHomePage bugstoreHomePage;

	private LoginPage loginPage;

	public AuthStepDefinitions() {
		super();

		Given("I login with valid credentials", () -> {
			groupSelectionPage = PageFactory.initElements(getDriver(), GroupSelectionPage.class);
			groupSelectionPage.visit();
			groupSelectionPage.selectGroup(15);
			bugstoreHomePage = groupSelectionPage.submitGroupSelection();

			loginPage = bugstoreHomePage.clickLogin();
			loginPage.enterCredentials("user15.b@mail.com", "T2022-x274");
			bugstoreHomePage = loginPage.successfulLogin();
		});
	}

}
