Feature: Add employees

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add employee button

  @dropdown
  Scenario: Adding one employee from feature file
    * user enters firstname and lastname
    * user clicks on save button
    * employee added successfully

  @ddt
  Scenario Outline: Add employee
    And user enters "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee added successfully

    Examples:
    |firstName|middleName|lastName|
    |test123  |MS        |test456 |
    |testabc  |MS        |testxyz |
    |test987  |MS        |test321 |

  @sampleCucumberScenario
  Scenario: Adding one employee using cucumber feature
    And user enters direct data "sebastianfn" "christinemn" and "yogitaln"
    And user clicks on save button
    Then employee added successfully

  @datatable
  Scenario: Add employee

    When user add multiple employees and verify they are added
    |firstName|middleName|lastName|
    |test123  |MS        |test456 |
    |testabc  |MS        |testxyz |
    |test987  |MS        |test321 |

   @excel
   Scenario: Adding employees from excel file
    When user adds multiple employees from excel file using "EmployeeData" sheet and verify the added employee

     @db
     Scenario: Front end and backend Testing
       When user enters direct data "Bla" "Olga" and "Bla"
       And capture the employee id
       And user clicks on save button
       And employee added successfully
       And query the information from backend
       Then verify the results from UI and Backend

       @employee_Id
       Scenario: Testing the Employee Ids are being stored inside the Employee_id in hs_hr_employees Table
         When user enters direct data "Blahahha" "Olga" and "Bla"
         And capture the employee id
         And user clicks on save button
         And employee added successfully
         And query the information from backend
         Then verify the results from front end and Backend
