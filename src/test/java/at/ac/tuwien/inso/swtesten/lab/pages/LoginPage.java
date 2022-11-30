package at.ac.tuwien.inso.swtesten.lab.pages;

import at.ac.tuwien.inso.swtesten.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {


	@FindBy(xpath = "//input[@type='email']")
	private WebElement usernameEmailInputField;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordInputField;

	@FindBy(id = "loginSubmit")
	private WebElement submitButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterCredentials(String email, String password){
		usernameEmailInputField.clear();
		usernameEmailInputField.sendKeys(email);

		passwordInputField.clear();
		passwordInputField.sendKeys(password);
	}

	public BugstoreHomePage successfulLogin(){
		submitButton.click();
		return initPage(BugstoreHomePage.class);
	}


}
