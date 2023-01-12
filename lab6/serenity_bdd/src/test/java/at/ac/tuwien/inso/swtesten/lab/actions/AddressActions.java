package at.ac.tuwien.inso.swtesten.lab.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
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

	@Step("Change street")
	public void changeStreet(String street) {
		streetInputField.clear();
		streetInputField.sendKeys(street);
	}

	@Step("Change zip")
	public void changeZip(String zip) {
		zipInputField.clear();
		zipInputField.sendKeys(zip);
	}

	@Step("Change city")
	public void changeCity(String city) {
		cityInputField.clear();
		cityInputField.sendKeys(city);
	}

	@Step("Change country")
	public void changeCountry(String country) {
		Select countrySelector = new Select(countrySelectorField);
		countrySelector.selectByValue(country.toUpperCase());
	}

	@Step("Click on submit")
	public void submit() {
		submitChangesButton.click();
	}
}
