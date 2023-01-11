package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.Link;
import net.thucydides.core.annotations.Step;

public class HomepageNavigateActions extends UIInteractions {
	@Step("Login")
	public void login(){
		$(Link.withNameOrId("loginButton")).click();

		$("//input[@type='email']").clear();
		$("//input[@type='email']").sendKeys("user15.a@mail.com");

		$("//input[@type='password']").clear();
		$("//input[@type='password']").sendKeys("user15.a@mail.com");

		$(Button.withNameOrId("loginSubmit")).click();
	}

	@Step("Navigate to homepage")
	public void toHomepage() {
		openUrl("https://peso.inso.tuwien.ac.at/st/v1/");
		$(Dropdown.withNameOrId("group")).selectByValue("grp15");
		$(Button.withText("Submit")).click();
	}

	@Step("Navigate to account info")
	public void toAccountInfo() {
		toHomepage();
		login();

		$(Link.withText("Settings")).click();
		$(Link.withText("Account")).click();
	}

	@Step("Navigate to payment methods")
	public void toPaymentMethods() {

	}

	@Step("Navigate to shopping cart")
	public void toShoppingCart() {

	}

	@Step("Navigate to item")
	public void toItem(String item) {

	}
}
