Feature: Adding a new payment option

  As a customer I want to define a new payment option.

  Scenario Outline: A new credit card can be registered as a payment option
	Given I login with valid credentials
	When I navigate to my payment methods
	And I add a new credit card with number "<account>", owner "<owner>" and valid thru date "<month>" "<year>"
	Then I see my newly added credit card in my payment methods

	Examples:
	  | account          | owner              | month | year |
	  | 1234000077779900 | Karl Mayer         | 02    | 2023 |
	  | 1111444477775555 | Johanna Hohenegger | 12    | 2025 |
