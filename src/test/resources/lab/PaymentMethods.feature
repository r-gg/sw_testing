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

  Scenario Outline: The created credit card payment method can be deleted
	Given I login with valid credentials
	When I navigate to my payment methods
	And I delete credit card with the number "<account>"
	And I confirm the deletion
	Then I cannot see anymore credit card "<account>" in my payment methods

	Examples:
	  | account          |
	  | 1234000077779900 |
	  | 1111444477775555 |

  Scenario Outline: A new credit card cannot be registered as a payment option
	Given I login with valid credentials
	When I navigate to my payment methods
	And I add a new credit card with number "<account>", owner "<owner>" and valid thru date "<month>" "<year>"
	Then I see validation error in the dialog

	Examples:
	  | account          | owner      | month | year |
	  | 1234000077779900 | Karl Mayer | 02    | 2022 |
	  | 1111444477775555 |            | 12    | 2025 |
	  | 775555           | Max        | 11    | 2023 |
