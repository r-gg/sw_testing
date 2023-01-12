package at.ac.tuwien.inso.swtesten.lab.pagecomponents;

import net.serenitybdd.core.pages.PageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShoppingCartResult extends PageComponent {

	public Boolean isItemInShoppingCart(String item, String amount, String price) {
		String itemPath = "//tr/td/a[normalize-space()='" + item + "']/../..";
		WebElement tableRow = getDriver().findElement(By.xpath(itemPath));
		List<WebElement> tableCells = tableRow.findElements(By.tagName("td"));

		return tableCells.get(0).getText().equals(item) &&
				tableCells.get(1).findElement(By.xpath("//input[@name='amount']")).getAttribute("value").equals(amount) &&
				tableCells.get(3).getText().substring(2).equals(price);
	}

	public Boolean isOrderSuccessfullyPlaced() {
		String pattern = "MMMM d, yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String date = dateFormat.format(new Date());

		String searchText = "Order from " + date;
		return getDriver().findElement(By.xpath("//p[contains(text(), '" + searchText + "')]")) != null;
	}
}
