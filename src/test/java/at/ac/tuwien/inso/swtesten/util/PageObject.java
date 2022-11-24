package at.ac.tuwien.inso.swtesten.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Common base class for all page objects.
 */
public class PageObject {

	protected WebDriver driver;

	public PageObject(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Initializes the subsequent page and all annotated WebElements within in
	 */
	public <T extends PageObject> T initPage(Class<T> pageObject) {
		return PageFactory.initElements(driver, pageObject);
	}
}
