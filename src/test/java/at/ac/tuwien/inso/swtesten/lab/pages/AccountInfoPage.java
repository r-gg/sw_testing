package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import org.junit.jupiter.api.Assertions.*;

public class AccountInfoPage extends PageObject {

	@FindBy(id = "firstname")
	private WebElement firstNameInputField;

	@FindBy(id = "lastname")
	private WebElement lastNameInputField;

	@FindBy(id = "dayOfBirthPicker")
	private WebElement dateOfBirthPicker;

	@FindBy(id = "address.street")
	private WebElement streetInputField;

	@FindBy(id = "address.city")
	private WebElement cityInputField;

	@FindBy(id = "address.zip")
	private WebElement zipInputField;

	@FindBy(id = "address.country")
	private WebElement countrySelectorField;

	@FindBy(xpath = "//button[contains(.,'Submit Changes')]")
	private WebElement submitChangesButton;

	@FindBy(xpath = "//div[@class='callout success autoclose']")
	private WebElement confirmationMessage;

	public AccountInfoPage(WebDriver driver) {
		super(driver);
	}

	public void enterFirstName(String firstName){
		firstNameInputField.clear();
		firstNameInputField.sendKeys(firstName);
	}

	public void enterLastName(String lastName){
		lastNameInputField.clear();
		lastNameInputField.sendKeys(lastName);
	}

	public void enterStreet(String street){
		streetInputField.clear();
		streetInputField.sendKeys(street);
	}

	public void enterZip(String zip){
		zipInputField.clear();
		zipInputField.sendKeys(zip);
	}

	public void enterCity(String city){
		cityInputField.clear();
		cityInputField.sendKeys(city);
	}

	public void selectCountry(String country){
		Select countrySelector = new Select(countrySelectorField);
		countrySelector.selectByValue(country.toUpperCase());
	}

	public void submitChanges(){
		submitChangesButton.click();
	}

	public void changeAddress(String street, String zip, String city, String country){
		this.enterStreet(street);
		this.enterZip(zip);
		this.enterCity(city);
		this.selectCountry(country);
	}

	public void assertConfirmationNotificationShown(){
		Assertions.assertTrue(confirmationMessage.isDisplayed());
	}

	public void assertAddressShown(String street, String zip, String city, String country){
		Assertions.assertEquals(street,streetInputField.getAttribute("value"));
		Assertions.assertEquals(zip,zipInputField.getAttribute("value"));
		Assertions.assertEquals(city,cityInputField.getAttribute("value"));

		Select countrySelector = new Select(countrySelectorField);
		WebElement selectedCountry = countrySelector.getFirstSelectedOption();
		Assertions.assertEquals(country.toUpperCase(),selectedCountry.getAttribute("value"));
	}



}
