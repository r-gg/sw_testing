package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.context.Context;
import at.ac.tuwien.inso.swtesten.lab.context.ScenarioContext;
import at.ac.tuwien.inso.swtesten.lab.pages.PaymentMethodsPage;

public class PaymentMethodsStepDefinitions extends BugstoreStepDefinitions {

	private ScenarioContext context;

	private PaymentMethodsPage paymentMethodsPage;

	public PaymentMethodsStepDefinitions() {
		super();

		Before(() -> {
			context = new ScenarioContext();
		});

		When("I navigate to my payment methods", () -> {
			paymentMethodsPage = getHomePage().navigateToPaymentMethods();
		});

		When("I add a new credit card with number {string}, owner {string} and valid thru date {string} {string}",
				(String account, String owner, String month, String year) -> {
					context.setContext(Context.CARD_NUMBER, account);
					context.setContext(Context.CARD_OWNER, owner);
					context.setContext(Context.CARD_THRU_MONTH, month);
					context.setContext(Context.CARD_THRU_YEAR, year);

					paymentMethodsPage.openNewPaymentDialog();
					paymentMethodsPage.openNewCreditCardTab();
					paymentMethodsPage.createCreditCardMethod(account, owner, month, year);
				});

		Then("I see my newly added credit card in my payment methods", () -> {
			paymentMethodsPage.assertCreditCardCreated(
					context.getContext(Context.CARD_NUMBER),
					context.getContext(Context.CARD_OWNER),
					context.getContext(Context.CARD_THRU_MONTH),
					context.getContext(Context.CARD_THRU_YEAR)
			);
		});

		When("I delete credit card with the number {string}", (String account) -> {
			paymentMethodsPage.deleteCreditCard(account);
		});

		Then("I cannot see anymore credit card {string} in my payment methods", (String account) -> {
			paymentMethodsPage.assertCreditCardDeleted(account);
		});

		Then("I see validation error in the dialog", () -> {
			paymentMethodsPage.assertValidationErrorOnCreate();
		});
	}
}
