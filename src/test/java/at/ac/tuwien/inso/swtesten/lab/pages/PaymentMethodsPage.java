package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PaymentMethodsPage extends PageObject {

	@FindBy(xpath = "//button[@aria-controls='addNewPaymentMethodModal']")
	private WebElement addButton;

	@FindBy(xpath = "//a[@id='addCreditCard-label']")
	private WebElement creditCardBtn;

	@FindBy(xpath = "//div[@id='addCreditCard']//input[@name='account']")
	private WebElement cardNumberModalField;

	@FindBy(xpath = "//div[@id='addCreditCard']//input[@name='owner']")
	private WebElement cardOwnerModalField;

	@FindBy(xpath = "//div[@id='addCreditCard']//button//span[text()='Save']")
	private WebElement modalSaveBtn;

	@FindBy(xpath = "//ul[@class='accordion']")
	private WebElement paymentMethodsList;

	private Select cardMonthModalSelect;

	private Select cardYearModalSelect;

	private int numberOfMethods = 0;

	public PaymentMethodsPage(WebDriver driver) {
		super(driver);
	}

	public void openNewPaymentDialog() {
		// store number of methods before dialog is open
		numberOfMethods = getNumberOfMethods();

		addButton.click();
	}

	private int getNumberOfMethods() {
		return paymentMethodsList.findElements(By.xpath("//li[@class='accordion-item']")).size();
	}

	public void openNewCreditCardTab() {
		creditCardBtn.click();

		cardMonthModalSelect = new Select(driver.findElement(By.xpath("//div[@id='addCreditCard']//select[@name='month']")));
		cardYearModalSelect = new Select(driver.findElement(By.xpath("//div[@id='addCreditCard']//select[@name='year']")));
	}

	public void createCreditCardMethod(String cardNumber,
									   String owner,
									   String month,
									   String year) {
		cardNumberModalField.clear();
		cardOwnerModalField.clear();

		cardNumberModalField.sendKeys(cardNumber);
		cardOwnerModalField.sendKeys(owner);

		cardMonthModalSelect.selectByValue(Integer.valueOf(month).toString());
		cardYearModalSelect.selectByValue(year);

		modalSaveBtn.click();
	}

	public void assertCreditCardCreated(String account, String owner, String month, String year) {
		Assertions.assertEquals(numberOfMethods + 1, getNumberOfMethods());

		WebElement lastItem = paymentMethodsList.findElement(
				By.xpath("(//li[@class='accordion-item'])[last()]//input[@name='account']"));

		Assertions.assertEquals(account, lastItem.getAttribute("value"));
//		Assertions.assertEquals(owner, "");
//		Assertions.assertEquals(month, "");
//		Assertions.assertEquals(year, "");
	}
}
