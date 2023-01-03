import constants from "./constants";

const navigateToLogin = async (page) => {
    await page.goto(constants.URL);
    await page.locator("//select[@name='group']").selectOption("grp15");
    await page.locator("//input[@value='Submit']").click();
    await page.locator("//a[@id='loginButton']").click();
};

const login = async (page, username, password) => {
    await page.locator("//input[@name='app_username']").fill(username);
    await page.locator("//input[@name='app_password']").fill(password);
    await page.locator("//input[@id='loginSubmit']").click();
};

export {
    navigateToLogin,
    login
}