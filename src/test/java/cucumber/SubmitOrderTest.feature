@tag
Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce web site

  @tag2
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | username              |  password   | productName |
      | rahulshetty@gmail.com | Iamking@000 | LAPTOP      |