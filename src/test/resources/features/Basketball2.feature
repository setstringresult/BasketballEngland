Feature: BasketballEngland User Registration Form

  Scenario Outline: Register user with success result
    Given Use "<browser>"
    When The user is on the registration page
    And Fills form correctly
    And Submits the form
    Then Should be redirected to the confirmation page and get the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"
    Examples:
      |browser|
      |chrome|
      |edge|

  Scenario Outline: Lastname must be present during registration
    Given Use "<browser>"
    When The user is on the registration page
    And Fills form without last name
    And Submits the form
    Then Should get error about missing last name
    Examples:
      |browser|
      |chrome|
      |edge|

  Scenario Outline: Password and password confirmation must match during registration
    Given Use "<browser>"
    When The user is on the registration page
    And Fills form with non matching passwords
    And Submits the form
    Then Should get error about mismatched passwords
    Examples:
      |browser|
      |chrome|
      |edge|

  Scenario Outline: User must accept terms and conditions before submitting form
    Given Use "<browser>"
    When The user is on the registration page
    And Fills form and doesn't accept terms and conditions
    And Submits the form
    Then Should get error about non accepted terms and conditions
    Examples:
      |browser|
      |chrome|
      |edge|