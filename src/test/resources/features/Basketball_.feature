Feature: BasketballEngland

  #Background:


  Scenario Outline: Register user
    Given The user is on the registration page in <num>
 #   Given The user is on the registration page in "<browser>"
 #   When The user fills out the registration form with valid information
 #   And Submits the form
 #   Then The user should be receive a confirmation email
 #   And Should be redirected to the confirmation page and get the message "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"
 #   And Should be able to log in with the newly created credentials
    Examples:
      | browser | num |
      | chrome  | 1   |
      | edge    | 2   |
