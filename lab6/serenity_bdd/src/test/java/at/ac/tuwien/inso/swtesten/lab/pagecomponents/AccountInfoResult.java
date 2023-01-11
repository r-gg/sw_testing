package at.ac.tuwien.inso.swtesten.lab.pagecomponents;

import net.serenitybdd.core.pages.PageComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountInfoResult extends PageComponent {
	@FindBy(id = "address.street")
	private WebElement streetInputField;
	@FindBy(id = "address.city")
	private WebElement cityInputField;
	@FindBy(id = "address.zip")
	private WebElement zipInputField;
	@FindBy(id = "address.country")
	private WebElement countrySelectorField;

	public Boolean addressIsShown(String street, String city, String zip, String country) {
		return streetInputField.getAttribute("value").equals(street) && cityInputField.getAttribute("value").equals(city) && zipInputField.getAttribute("value").equals(zip) && new Select(countrySelectorField).getFirstSelectedOption().getAttribute("value").equals(country.toUpperCase());
	}
}
