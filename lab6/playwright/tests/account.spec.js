const { test, expect } = require('@playwright/test');
const { navigateToLogin, login } = require('./commonUtils');
const { default: constants } = require('./constants');

const INVALID_DATA = [
    { firstname: "John", lastname: "", birth: "06/13/1995", street: "Josefstaeder Strasse", town: "Wien", zip: "1080" },
    { firstname: "", lastname: "Doe", birth: "06/13/1995", street: "Josefstaeder Strasse", town: "Wien", zip: "1080" },
    { firstname: "John", lastname: "Doe", birth: "", street: "Josefstaeder Strasse", town: "Wien", zip: "1080" },
    { firstname: "John", lastname: "Doe", birth: "06/13/1995", street: "", town: "Wien", zip: "1080" },
    { firstname: "John", lastname: "Doe", birth: "06/13/1995", street: "Josefstaeder Strasse", town: "", zip: "1080" },
    { firstname: "John", lastname: "Doe", birth: "06/13/1995", street: "Josefstaeder Strasse", town: "Wien", zip: "" }
];

test.beforeEach(async ({ page }) => {
    await navigateToLogin(page);
    await login(page, constants.EMAIL, constants.PASSWORD);
});

test.describe("Edit account information details", () => {

    test.beforeEach(async ({ page }) => {
        await page.locator("//li[@aria-label='Settings']").hover();
        await page.locator("//a[contains(text(),'Account')]").click();
    });

    test("Change valid address", async ({ page }) => {
        await page.getByLabel("Street").fill("Josefstaeder Strasse");
        await page.getByLabel("Town").fill("Wien");
        await page.getByLabel("Zip Code").fill("1080");
        await page.getByLabel("Country").selectOption("AUSTRIA");
        await page.locator("//button[contains(.,'Submit Changes')]").click();

        const street = await page.getByLabel("Street").inputValue();
        const town = await page.getByLabel("Town").inputValue();
        const zip = await page.getByLabel("Zip Code").inputValue();
        const country = await page.getByLabel("Country").inputValue();

        expect(street).toBe("Josefstaeder Strasse");
        expect(town).toBe("Wien");
        expect(zip).toBe("1080");
        expect(country.toLowerCase()).toBe("austria");
    });

    test("Change invalid address", async ({ page }) => {
        for (const data of INVALID_DATA) {
            await page.getByLabel("Firstname").fill(data.firstname);
            await page.getByLabel("Lastname").fill(data.lastname);
            await page.getByLabel("Day of Birth").fill(data.birth);
            await page.getByLabel("Street").fill(data.street);
            await page.getByLabel("Town").fill(data.town);
            await page.getByLabel("Zip Code").fill(data.town);
            await page.locator("//button[contains(.,'Submit Changes')]").click();

            const alertLoc = page.locator("//div[@data-abide-error]");
            const display = await alertLoc.evaluate((e) => {
                return window.getComputedStyle(e).getPropertyValue("display");
            });
            expect(display).toBe("block");
        }
    });
})