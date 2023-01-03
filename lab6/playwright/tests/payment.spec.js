const { test, expect } = require('@playwright/test');
const { navigateToLogin, login } = require('./commonUtils');
const { default: constants } = require('./constants');

const VALIDATION_DATA = [
    { account: "1234000077779900", owner: "Max Mustermann", month: "1", year: "2023" },
    { account: "1111444477775555", owner: "", month: "12", year: "2025" },
    { account: "775555", owner: "Max Mustermann", month: "11", year: "2023" }
]

test.beforeEach(async ({ page }) => {
    await navigateToLogin(page);
    await login(page, constants.EMAIL, constants.PASSWORD);
});

test.describe.configure({ mode: 'serial' });
test.describe('Payment methods', () => {

    test.beforeEach(async ({ page }) => {
        await page.locator("//li[@aria-label='Settings']").hover();
        await page.locator("//a[contains(text(),'Payment Method')]").click();
    });

    test('Add new credit card as payment method', async ({ page }) => {
        const counter = await page.locator("//li[@data-accordion-item]").count();
        // open new payment method dialog
        await page.locator("//button[@aria-controls='addNewPaymentMethodModal']").click();
        // switch to credit card tab
        await page.locator("//a[@id='addCreditCard-label']").click();

        // fill the data
        await page.locator("//div[@id='addCreditCard']//input[@name='account']").fill("1234000077779900");
        await page.locator("//div[@id='addCreditCard']//input[@name='owner']").fill("Max Mustermann");
        await page.locator("//div[@id='addCreditCard']//select[@name='month']").selectOption("12");
        await page.locator("//div[@id='addCreditCard']//select[@name='year']").selectOption("2025");

        // save data
        await page.locator("//div[@id='addCreditCard']//button//span[text()='Save']").click();

        // check that payment method is created
        await page.waitForTimeout(500);
        const newSize = await page.locator("//li[@data-accordion-item]").count();
        expect(newSize).toBe(counter + 1);
    });

    test("A new credit card cannot be registered", async ({ page }) => {
        // open new payment method dialog
        await page.locator("//button[@aria-controls='addNewPaymentMethodModal']").click();
        // switch to credit card tab
        await page.locator("//a[@id='addCreditCard-label']").click();

        const areaLoc = page.locator("//div[@id='addCreditCard']");

        for (const card of VALIDATION_DATA) {
            // fill the data
            const accountLoc = page.locator("//div[@id='addCreditCard']//input[@name='account']");
            const ownerLoc = page.locator("//div[@id='addCreditCard']//input[@name='owner']");
            await accountLoc.clear();
            await ownerLoc.clear();
            await accountLoc.fill(card.account);
            await ownerLoc.fill(card.owner);
            await page.locator("//div[@id='addCreditCard']//select[@name='month']").selectOption(card.month);
            await page.locator("//div[@id='addCreditCard']//select[@name='year']").selectOption(card.year);
            // save data
            await page.locator("//div[@id='addCreditCard']//button//span[text()='Save']").click();

            const alertLoc = areaLoc.locator("//div[@data-abide-error]");
            const display = await alertLoc.evaluate((e) => {
                return window.getComputedStyle(e).getPropertyValue("display");
            });
            expect(display).toBe("block");
        }
    });

    test('Delete credit card as a payment method', async ({ page }) => {
        // get first counter of available payment methods
        const counter = await page.locator("//li[@data-accordion-item]").count();

        // expand payment item (assume we have only credit cards in test env)
        await page.getByRole("tab")
            .filter({ hasText: "Creditcard" })
            .first()
            .click();

        // wait for expand animation to become visible
        await page.waitForTimeout(500);

        // click on delete button
        await page.locator("//button[contains(@aria-controls, 'deleteModal')]")
            .first()
            .click();

        // confirm deletion from model
        const modalLocator = page.locator("//div[@role='dialog' and @aria-hidden='false']");
        await modalLocator.getByRole('button', { name: "Delete" }).click();

        // check that payment method is deleted
        await page.waitForTimeout(500);
        const newSize = await page.locator("//li[@data-accordion-item]").count();
        expect(newSize).toBe(counter - 1);
    });
});