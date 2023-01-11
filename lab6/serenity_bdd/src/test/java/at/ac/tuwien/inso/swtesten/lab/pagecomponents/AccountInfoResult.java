package at.ac.tuwien.inso.swtesten.lab.pagecomponents;

import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.InputField;

public class AccountInfoResult extends PageComponent {
	public Boolean confirmationVisible() {
		return !$("//div[contains(@class,'success')]").isVisible();
	}

	public Boolean addressIsShown(String street, String city, String zip, String country) {
		return $(InputField.withNameOrId("address.street")).getValue().equals(street) &&
		$(InputField.withNameOrId("address.city")).getValue().equals(city) &&
		$(InputField.withNameOrId("address.zip")).getValue().equals(zip) &&
		$(Dropdown.withNameOrId("address.country")).getFirstSelectedOptionValue().equals(country.toUpperCase());
	}
}
