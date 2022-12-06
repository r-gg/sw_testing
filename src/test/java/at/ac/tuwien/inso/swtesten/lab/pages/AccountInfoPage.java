package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.lab.context.Context;
import at.ac.tuwien.inso.swtesten.lab.context.ScenarioContext;
import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

	@FindBy(xpath = "//div[contains(@class,'alert')]")
	private List<WebElement> errorMessage;



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

	public void enderBirthDate(String dateOfBirth){
		dateOfBirthPicker.clear();
		dateOfBirthPicker.sendKeys(dateOfBirth);
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

	public void changeName(String firstname, String lastname){
		this.enterFirstName(firstname);
		this.enterLastName(lastname);
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

	public void assertNameDataShown(String firstname, String lastname){
		Assertions.assertEquals(firstname,firstNameInputField.getAttribute("value"));
		Assertions.assertEquals(lastname,lastNameInputField.getAttribute("value"));
	}

	public void assertDateOfBirthShown(String dateOfBirth){
		Assertions.assertEquals(dateOfBirth,dateOfBirthPicker.getAttribute("value"));
	}

	public void assertErrorMessageShown(){
		Assertions.assertTrue(errorMessage.stream().anyMatch(WebElement::isDisplayed));
	}

	public void fillContextWithCurrentAddressData(ScenarioContext scenarioContext){
		scenarioContext.setContext(Context.STREET, streetInputField.getAttribute("value"));
		scenarioContext.setContext(Context.ZIP, zipInputField.getAttribute("value"));
		scenarioContext.setContext(Context.CITY, cityInputField.getAttribute("value"));
		Select countrySelector = new Select(countrySelectorField);
		WebElement selectedCountry = countrySelector.getFirstSelectedOption();
		scenarioContext.setContext(Context.COUNTRY, selectedCountry.getAttribute("value"));
	}

	public void fillContextWithCurrentDateOfBirthData(ScenarioContext scenarioContext){
		scenarioContext.setContext(Context.DATE_OF_BIRTH, dateOfBirthPicker.getAttribute("value"));

	}

	public void fillContextWithCurrentNameData(ScenarioContext scenarioContext){
		scenarioContext.setContext(Context.FIRST_NAME, firstNameInputField.getAttribute("value"));
		scenarioContext.setContext(Context.LAST_NAME, lastNameInputField.getAttribute("value"));
	}



}
