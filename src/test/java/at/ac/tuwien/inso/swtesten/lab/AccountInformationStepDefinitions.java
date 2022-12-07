package at.ac.tuwien.inso.swtesten.lab;

import at.ac.tuwien.inso.swtesten.lab.context.Context;
import at.ac.tuwien.inso.swtesten.lab.context.ScenarioContext;
import at.ac.tuwien.inso.swtesten.lab.pages.AccountInfoPage;

import java.util.Calendar;

public class AccountInformationStepDefinitions extends BugstoreStepDefinitions {

	private ScenarioContext newDataScenarioContext;

	private ScenarioContext oldDataScenarioContext;

	private AccountInfoPage accountInfoPage;

	public AccountInformationStepDefinitions() {
		super();

		Before(() -> {
			newDataScenarioContext = new ScenarioContext();
			oldDataScenarioContext = new ScenarioContext();
		});

		When("I navigate to my profile", () -> {
			accountInfoPage = getHomePage().navigateToAccountInfo();
		});

		When("I change my address to {string}, {string}, {string}, {string}",
				(String street, String zip, String city, String country) -> {
					accountInfoPage.fillContextWithCurrentAddressData(oldDataScenarioContext);
					accountInfoPage.changeAddress(street, zip, city, country);
					newDataScenarioContext.setContext(Context.STREET, street);
					newDataScenarioContext.setContext(Context.ZIP, zip);
					newDataScenarioContext.setContext(Context.CITY, city);
					newDataScenarioContext.setContext(Context.COUNTRY, country);
				});

		When("I change my name to {string} {string}",
				(String firstname, String lastname) -> {
					accountInfoPage.fillContextWithCurrentNameData(oldDataScenarioContext);
					accountInfoPage.changeName(firstname, lastname);
					newDataScenarioContext.setContext(Context.FIRST_NAME, firstname);
					newDataScenarioContext.setContext(Context.LAST_NAME, lastname);
				});

		When("I change my date of birth to {string}", (String dateOfBirth) -> {
			accountInfoPage.fillContextWithCurrentDateOfBirthData(oldDataScenarioContext);
			accountInfoPage.enderBirthDate(dateOfBirth);
			newDataScenarioContext.setContext(Context.DATE_OF_BIRTH, dateOfBirth);
		});

		When("I submit my changes", () -> {
			accountInfoPage.submitChanges();
		});

		Then("A confirmation notification is shown", () -> {
			accountInfoPage.assertConfirmationNotificationShown();
		});

		Then("I can see my new address on my profile", () -> {
			accountInfoPage.assertAddressShown(
					newDataScenarioContext.getContext(Context.STREET),
					newDataScenarioContext.getContext(Context.ZIP),
					newDataScenarioContext.getContext(Context.CITY),
					newDataScenarioContext.getContext(Context.COUNTRY));

			getDriver().get(getDriver().getCurrentUrl());
			accountInfoPage.assertAddressShown(
					newDataScenarioContext.getContext(Context.STREET),
					newDataScenarioContext.getContext(Context.ZIP),
					newDataScenarioContext.getContext(Context.CITY),
					newDataScenarioContext.getContext(Context.COUNTRY));
		});

		Then("I can see my new birth date on my profile", () -> {
			accountInfoPage.assertDateOfBirthShown(newDataScenarioContext.getContext(Context.DATE_OF_BIRTH));

			getDriver().get(getDriver().getCurrentUrl());
			accountInfoPage.assertDateOfBirthShown(newDataScenarioContext.getContext(Context.DATE_OF_BIRTH));
		});

		Then("An error message is shown", () -> {
			accountInfoPage.assertErrorMessageShown();
		});

		Then("My old data is unchanged", () -> {
			getDriver().get(getDriver().getCurrentUrl());
			accountInfoPage.assertNameDataShown(
					oldDataScenarioContext.getContext(Context.FIRST_NAME),
					oldDataScenarioContext.getContext(Context.LAST_NAME)
			);

			accountInfoPage.assertDateOfBirthShown(
					oldDataScenarioContext.getContext(Context.DATE_OF_BIRTH)
			);
			accountInfoPage.assertAddressShown(
					oldDataScenarioContext.getContext(Context.STREET),
					oldDataScenarioContext.getContext(Context.ZIP),
					oldDataScenarioContext.getContext(Context.CITY),
					oldDataScenarioContext.getContext(Context.COUNTRY));
		});

		Given("I set my birth date to {int} days before the date that was 18 years ago", (Integer daysBefore) -> {
			Calendar date = Calendar.getInstance();
			date.add(Calendar.YEAR, -18); // 18 years ago
			date.add(Calendar.DATE, -daysBefore); // daysBefore days before

			int dateInt = date.get(Calendar.DATE);
			int monthInt = date.get(Calendar.MONTH) + 1;
			String dateStr = (dateInt < 10) ? "0" + dateInt : "" + dateInt;
			String monthStr = (monthInt < 10) ? "0" + monthInt : "" + monthInt;
			String dateString = monthStr + "/" + dateStr + "/" + date.get(Calendar.YEAR);

			accountInfoPage.fillContextWithCurrentDateOfBirthData(oldDataScenarioContext);
			accountInfoPage.enderBirthDate(dateString);
			newDataScenarioContext.setContext(Context.DATE_OF_BIRTH, dateString);
		});

		Given("I set my birth date to {int} days after the date that was 18 years ago", (Integer daysAfter) -> {
			Calendar date = Calendar.getInstance();
			date.add(Calendar.YEAR, -18); // 18 years ago
			date.add(Calendar.DATE, daysAfter); // daysAfter days after

			int dateInt = date.get(Calendar.DATE);
			int monthInt = date.get(Calendar.MONTH) + 1;
			String dateStr = (dateInt < 10) ? "0" + dateInt : "" + dateInt;
			String monthStr = (monthInt < 10) ? "0" + monthInt : "" + monthInt;
			String dateString = monthStr + "/" + dateStr + "/" + date.get(Calendar.YEAR);

			accountInfoPage.fillContextWithCurrentDateOfBirthData(oldDataScenarioContext);
			accountInfoPage.enderBirthDate(dateString);
			newDataScenarioContext.setContext(Context.DATE_OF_BIRTH, dateString);

		});

		Then("My old date of birth is unchanged", () -> {
			getDriver().get(getDriver().getCurrentUrl());
			accountInfoPage.assertDateOfBirthShown(
					oldDataScenarioContext.getContext(Context.DATE_OF_BIRTH)
			);
		});
	}



}
