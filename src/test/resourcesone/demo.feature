Feature: SauceDemo End-to-End Order Flow

  Scenario Outline: Login and place an order
  
    Given User launches the SauceDemo application
    When User verifies the title of the page
    When User logs in with valid credentials
    And User adds multiple items to the cart
    And User proceeds to checkout and provides information
    And User clicks on finish button
    Then Order confirmation message should be displayed as "Thank you for your order!"

