package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.InputField;

import java.util.Locale;

public class AddressActions extends UIInteractions {

	public void changeStreet(String street) {
		$(InputField.withNameOrId("address.street")).clear();
		$(InputField.withNameOrId("address.street")).sendKeys(street);
	}

	public void changeCity(String city) {
		$(InputField.withNameOrId("address.city")).clear();
		$(InputField.withNameOrId("address.city")).sendKeys(city);
	}

	public void changeZip(String zip) {
		$(InputField.withNameOrId("address.zip")).clear();
		$(InputField.withNameOrId("address.zip")).sendKeys(zip);
	}

	public void changeCountry(String country) {
		$(Dropdown.withNameOrId("address.country")).selectByValue(country.toUpperCase());
		$(Button.withNameOrId("submitSearchButton")).click();
	}

	public void submit(){
		$(Button.withText("Submit Changes")).click();
	}
}
