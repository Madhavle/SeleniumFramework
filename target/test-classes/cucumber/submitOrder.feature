
@tag
Feature: Submit the order from Ecommrce website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommrce page

  @SubmitOrder
  Scenario Outline: Positive test of submitting the order
    Given Logged in with <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name     | password  | productName     |
      | a@ml.com | Madhav@99 | ADIDAS ORIGINAL |