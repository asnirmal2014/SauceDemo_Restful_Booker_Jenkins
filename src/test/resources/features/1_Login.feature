
@smoke
Feature: SauceDemo login

  As a shopper
  I want to log into SauceDemo
  So that I can buy products

  Background:
    Given I am on the SauceDemo login page

  Scenario: Successful login with valid credentials
    When I log in as "standard_user" with password "secret_sauce"
    Then I should see the products page

  Scenario Outline: Login with invalid credentials
    When I log in as "<user>" with password "<password>"
    Then I should see the login error message

    Examples:
      | user            | password     |
      | locked_out_user | secret_sauce |
      | standard_user   | wrongpass    |
