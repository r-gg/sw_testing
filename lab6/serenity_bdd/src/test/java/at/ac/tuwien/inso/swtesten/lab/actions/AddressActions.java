package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.ui.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddressActions extends UIInteractions {

	@FindBy(xpath = "//button[contains(.,'Submit Changes')]")
	private WebElement submitChangesButton;
	@FindBy(id = "address.street")
	private WebElement streetInputField;
	@FindBy(id = "address.city")
	private WebElement cityInputField;
	@FindBy(id = "address.zip")
	private WebElement zipInputField;
	@FindBy(id = "address.country")
	private WebElement countrySelectorField;

	public void changeStreet(String street) {
		streetInputField.clear();
		streetInputField.sendKeys(street);
	}

	public void changeZip(String zip) {
		zipInputField.clear();
		zipInputField.sendKeys(zip);
	}

	public void changeCity(String city) {
		cityInputField.clear();
		cityInputField.sendKeys(city);
	}

	public void changeCountry(String country) {
		Select countrySelector = new Select(countrySelectorField);
		countrySelector.selectByValue(country.toUpperCase());
	}

	public void submit() {
		submitChangesButton.click();
	}
}
