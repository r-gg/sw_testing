Feature: Adding items to the shopping cart

  As a customer I want to add items to my shopping cart and submit my order.

  Scenario Outline: Items can be placed in the shopping cart and order can be submitted
    Given I login with valid credentials
    When I search for "<searchString>"
    Then I expect "<found>" item to be found
    When I open the details of "<item>"
    And I add "<amount>" items to my shopping cart
    And I navigate to my cart
    Then I expect "<amount>" "<item>" in my cart with the total price "<price>"
    When I submit my order
    Then my order is successfully placed

    Examples:
      | searchString | found | item                      | amount | price  |
      | Thunder      | 1     | Mozilla Thunderbird       | 3      | 41.97  |
      | Apple        | 2     | Apple I                   | 1      | 195.99 |
      | the          | 3     | The First Men in the Moon | 3      | 17.97  |
