Feature: Sauce Demo Checkout

  Scenario: Checkout Successful
    Given I am on the Login Page of Sauce Demo
    And I login as buyer
    When I add first item to the shopping cart
    And I check the item is correct
    When I proceed to checkout
    And I complete checkout
    Then I should be taken to Complete Checkout Page
    And I logout
