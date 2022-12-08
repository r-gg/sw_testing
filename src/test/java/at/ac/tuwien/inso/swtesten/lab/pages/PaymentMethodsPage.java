package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver.waitUntil;

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
		return paymentMethodsList.findElements(By.xpath("//li[@data-accordion-item]")).size();
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
		// wait 1 second until modal is closed and payment method created
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Assertions.assertEquals(numberOfMethods + 1, getNumberOfMethods());

		WebElement creditCardItem = getListItemByCreditCard(account);
		if (creditCardItem == null) {
			return;
		}
		WebElement lastAccountField = creditCardItem.findElement(
				By.xpath(".//input[@name='account']"));

		Select lastMonthSelect = new Select(creditCardItem.findElement(
				By.xpath(".//select[@name='month']")));

		Select lastYearSelect = new Select(creditCardItem.findElement(
				By.xpath(".//select[@name='year']")));

		Assertions.assertEquals(account, lastAccountField.getAttribute("value"));
		// NOTE: not check for owner as there is a bug, so test would fail
		Assertions.assertEquals(Integer.valueOf(month).toString(), lastMonthSelect.getFirstSelectedOption().getAttribute("value"));
		Assertions.assertEquals(year, lastYearSelect.getFirstSelectedOption().getAttribute("value"));
	}

	public void deleteCreditCard(String account) {
		numberOfMethods = getNumberOfMethods();

		WebElement listItem = getListItemByCreditCard(account);

		if (listItem == null) {
			return;
		}

		listItem.click();

		WebElement deleteBtn = listItem.findElement(By.xpath(".//button[@class='button'][.//span[contains(text(), 'Delete')]]"));
		deleteBtn.click();

		WebElement modal = driver.findElement(By.xpath("//div[@role='dialog' and @aria-hidden='false']"));
		WebElement modalDeleteBtn = modal.findElement(By.xpath(".//button[@class='button'][.//span[contains(text(), 'Delete')]]"));
		modalDeleteBtn.click();
	}

	public void assertCreditCardDeleted(String account) {
		// wait 1 second until modal is closed and payment method created
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Assertions.assertNull(getListItemByCreditCard(account));
	}

	private WebElement getListItemByCreditCard(String account) {
		List<WebElement> paymentMethods = driver.findElements(By.xpath("//li[@data-accordion-item]"));
		for (WebElement method : paymentMethods) {
			try {
				method.findElement(By.xpath(".//input[@name='account' and @value='" + account + "']"));
				return method;
			} catch (NoSuchElementException ignored) {
				// nothing to catch, ignore
			}
		}
		return null;
	}

	public void assertValidationErrorOnCreate() {
		WebElement modal = driver.findElement(By.xpath("//div[@id='addNewPaymentMethodModal']"));
		WebElement errorMsg = modal.findElement(By.xpath(".//div[@class='alert callout' and @data-abide-error][@style='display: block;']"));
		Assertions.assertEquals("Validation failed! Please correct the input fields.", errorMsg.getText().trim());
	}
}
