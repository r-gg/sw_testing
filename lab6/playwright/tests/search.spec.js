const { test, expect } = require('@playwright/test');
const { navigateToLogin, login } = require('./commonUtils');
const { default: constants } = require('./constants');

const SEARCH_ITEMS = [
    { searchString: "A", found: 8 },
    { searchString: "Apple", found: 2 },
    { searchString: "Arizona Bound", found: 1 },
    { searchString: "pingus", found: 0 },
    { searchString: "Pingus", found: 1 },
    { searchString: "Arizona Boundies", found: 0 },
    { searchString: "ZZ", found: 0 }
];

test.beforeEach(async ({ page }) => {
    await navigateToLogin(page);
    await login(page, constants.EMAIL, constants.PASSWORD);
});

test.describe('Search items', () => {
    test.beforeEach(async ({ page }) => {
        await page.goto(constants.URL);
    });

    test('Search for item', async ({ page }) => {
        for (const item of SEARCH_ITEMS) {
            await page.locator("//input[@id='searchString']").fill(item.searchString);
            await page.locator("//button[@id='submitSearchButton']").click();
            const count = await page.locator("//a/div/img").count();
            await expect(count).toEqual(item.found);
        }
    });
});