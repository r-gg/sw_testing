package at.ac.tuwien.inso.swtesten.lab.pagecomponents;

import net.serenitybdd.core.pages.PageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PaymentMethodsResult extends PageComponent {
	public Boolean creditCardAdded(String cardNumber, String owner, String month, String year) {
		WebElement creditCard = getCreditCard(cardNumber);

		WebElement cardNumberInputField = creditCard.findElement(
				By.xpath(".//input[@name='account']"));

		Select monthSelect = new Select(creditCard.findElement(
				By.xpath(".//select[@name='month']")));

		Select yearSelect = new Select(creditCard.findElement(
				By.xpath(".//select[@name='year']")));

		return cardNumberInputField.getAttribute("value").equals(cardNumber) &&
				monthSelect.getFirstSelectedOption().getAttribute("value").equals(Integer.valueOf(month).toString()) &&
				yearSelect.getFirstSelectedOption().getAttribute("value").equals(year);
	}

	private WebElement getCreditCard(String account) {
		List<WebElement> paymentMethods = getDriver().findElements(By.xpath("//li[@data-accordion-item]"));
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
}
