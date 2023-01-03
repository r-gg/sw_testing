const { test, expect } = require('@playwright/test');
const { navigateToLogin, login } = require('./commonUtils');

test.describe('Login', () => {
    test('Use wrong credentials', async ({ page }) => {
        await navigateToLogin(page);
        await expect(page).toHaveURL(/.*login/);

        // wrong username
        await login(page, "user15.x@mail.com", "T2022-x274");
        await expect(page).toHaveURL(/.*login-error/);

        // wrong password
        await login(page, "user15.c@mail.com", "T2022-zzz274");
        await expect(page).toHaveURL(/.*login-error/);

        // wrong username and pw
        await login(page, "user15.x@mail.com", "Tasf274-as");
        await expect(page).toHaveURL(/.*login-error/);
    });

    test('Use valid credentials', async ({ page }) => {
        await navigateToLogin(page);
        await expect(page).toHaveURL(/.*login/);
        await login(page, "user15.c@mail.com", "T2022-x274");
        await expect(page.getByText("Logout")).toBeVisible();
    });
});