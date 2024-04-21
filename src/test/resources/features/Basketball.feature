Feature: BasketballEngland

  Background:


  Scenario: Register user
    Given The user is on the registration page
    When The user fills out the registration form with valid information
    And Submits the form
    Then The user should be receive a confirmation email
    And Should be redirected to the confirmation page and get the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"
    And Should be able to log in with the newly created credentials
