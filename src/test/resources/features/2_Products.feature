@regression
Feature: SauceDemo Products

  As a shopper
  I want to view and sort available products
  So that I can select products to purchase

  Background:
    Given I am logged in as "standard_user"

  Scenario: Verify products page is displayed
    Then I should see the products page
    And I should see 6 products displayed

  Scenario: Sort products by name in ascending order
    When I sort products by name in ascending order
    Then products should be displayed in alphabetical order

  Scenario: Sort products by price from low to high
    When I sort products by price from low to high
    Then products should be displayed from lowest to highest price
