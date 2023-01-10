package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.ui.Select;

public class HomepageNavigateActions extends UIInteractions {
	public void toHomepage() {
		openUrl("https://peso.inso.tuwien.ac.at/st/v1/");
		$(Dropdown.withNameOrId("group")).selectByValue("grp15");
		$(Button.withText("Submit")).click();
	}


	@Step("Navigate to account info")
	public void toAccountInfo() {

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
