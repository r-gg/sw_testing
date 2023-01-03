const { test, expect } = require('@playwright/test');
const { navigateToLogin, login } = require('./commonUtils');
const { default: constants } = require('./constants');

const ITEMS = [
    { title: "Arizona Bound" },
    { title: "King Kong" },
    { title: "PCC 2000" },
    { title: "Pingus" },
    { title: "The Doctor In War" }
];

test.beforeEach(async ({ page }) => {
    await navigateToLogin(page);
    await login(page, constants.EMAIL, constants.PASSWORD);
});

test.describe('Display items', () => {

    test.beforeEach(async ({ page }) => {
        await page.goto(constants.URL);
    });

    test('Items are correctly displayed on homepage', async ({ page }) => {
        for (const item of ITEMS) {
            await expect(page.locator(`//a/h5[contains(text(),'${item.title}')]`)).toBeVisible();
        }
    });
});