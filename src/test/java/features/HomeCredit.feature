@HomeCredit
Feature: TestingDemo
  As a user
  Register new User - Password Login Information
  Login to Bank Guru project
  Create new customer => Verify info
  Create new Account => Verify info
  Do deposite and verify deposit amount

  @registerPage
  Scenario: Register to application
    Given I get login URL
    And I click to here link
    And I input to email textbox type input with random email
    And I click to "Submit" button
    Then I get userID values
    And I get  password values
    When I open login page again

  @login
  Scenario: Login to application
    Given I input UserID textbox
    And I input Password textbox
    When I click to "LOGIN" button
    Then Verify Homepage is displayed with message "Welcome To Manager's Page of Guru99 Bank"

  @newCustomer
  Scenario Outline: Create new customer account
    Given I open "New Customer" Page
    And I input to "Customer Name" textbox type input with "<Customer Name>"
    And I check "Gender" checkbox with "<Gender>"
    And I input to "Date of Birth" datePicker with "<DOB>"
    And I input to "Address" textarea with "<Address>"
    And I input to "State" textbox type input with "<State>"
    And I input to "City" textbox type input with "<City>"
    And I input to "Mobile Number" textbox type input with "<Mobile Number>"
    And I input to "PIN" textbox type input with "<PIN>"
    And I input to "E-mail" textbox type input with "<E-mail>" random time value
    And I input to "Password" textbox type input with "<Password>"
    And I click to "Submit" button
    Then I verify message "Customer Registered Successfully!!!"
    And I get Customer ID
    And I verify value Table for "Customer Name" cell with "<Customer Name>"
    And I verify value Table for "Address" cell with "<Address>"
    And I verify value Table for "State" cell with "<State>"
    And I verify value Table for "City" cell with "<City>"
    And I verify value Table for "Mobile No." cell with "<Mobile Number>"
    And I verify value Table for "Address" cell with "<Address>"
    And I verify value Table for "Pin" cell with "<PIN>"
    And I verify value Table for "Email" cell with "<E-mail>"

    Examples: New customer info
      | Customer Name | Gender | DOB        | Address   | State   | City | Mobile Number | PIN    | E-mail  | Password |
      | Kim Huong     | f      | 31/05/1988 | Pham Hung | Sai Gon | HCM  |    0123456789 | 190195 | rdEmail |   123123 |

  @createAccount
  Scenario Outline: Create new account for this customer
    Given I open "New Account" Page
    And I input Customer ID
    And I input to "Initial deposit" textbox type input contains with "<DepositAmt>"
    And I click to "submit" button
    Then I verify message "Account Generated Successfully!!!"
    
    And I get Account ID
    And I verify Customer ID
    And I verify value Table for "Customer Name" cell with "<Name>"
    And I verify value Table for "Account Type" cell with "<AccountType>"
    And I verify value Table for "Current Amount" cell with "<DepositAmt>"

    Examples: New customer info
      | AccountType | DepositAmt | Name      |
      | Savings            |        500        | Kim Huong |
      
      
  @Deposite
  Scenario Outline: Do deposite for this account
    Given I open "Deposit" Page
    And I input Account ID
    And I input to "Amount" textbox type input with "<Amount>"
    And I input to "Description" textbox type input with "<Description>"
    And I click to "Submit" button
        
    And I verify value Table for "Amount Credited" cell with "<Amount>"
    And I verify value Table for "Description" cell with "<Description>"
    And I verify value Table for "Current Balance" cell with "<Balance>"

    Examples: New customer info
      | Amount | Description |Balance |
      | 50            |        Testing  |          550     |
