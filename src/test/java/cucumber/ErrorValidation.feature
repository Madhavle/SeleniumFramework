
@tag
Feature: Error validation


  @ErrorValidation
  Scenario Outline: Error validation test
    Given I landed on Ecommrce page
    Given Logged in with <name> and password <password>
    Then "Incorrect email or password." error message displayed

    Examples: 
      | name     | password  |
      | a@ml.com | Madhav    |
