@tag
  Feature: Error validation

    @tag2
    Scenario Outline:
      Given I landed on Ecommerce web site
      When Logged in with username <username> and password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | username               | password |
        | barak_obama@gmail.com  | test123 |