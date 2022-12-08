package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShoppingCartPage extends PageObject {

	@FindBy(xpath = "//button[span='Checkout']")
	private WebElement checkoutButton;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	public void assertItemInShoppingCart(String amount, String item, String price){
		String itemPath = "//tr/td/a[normalize-space()='" + item + "']/../..";
		WebElement tableRow = driver.findElement(By.xpath(itemPath));
		List<WebElement> tableCells = tableRow.findElements(By.tagName("td"));

		Assertions.assertEquals(item, tableCells.get(0).getText());
		Assertions.assertEquals(amount, tableCells.get(1).findElement(By.xpath("//input[@name='amount']")).getAttribute("value"));
		Assertions.assertEquals(price, tableCells.get(3).getText().substring(2));
	}

	public void submitOrder(){
		checkoutButton.click();
	}

	public void assertOrderSuccessfullyPlaced(){
		String pattern = "MMMM d, yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String date = dateFormat.format(new Date());

		String searchText = "Order from " + date;
		Assertions.assertNotNull(driver.findElement(By.xpath("//p[contains(text(), '" + searchText + "')]")));
	}
}
