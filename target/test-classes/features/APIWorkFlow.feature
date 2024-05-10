Feature: Syntax API workflow for HRMS

  Background:
    Given a JWT bearer token is generated

  @WorkFlow1
  Scenario: create an employee via API work flow
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @WorkFlow1
  Scenario: Retrieving recently created employee
    Given a request is prepared to get the created employee using api call
    When a GET call is made to retrieve the created employee
    Then the status code for this employee is 200
    And the employee data we get having ID "employee.employee_id" must match with the globally stored employee id
    And the retrieved data at "employee" object matches the data used to create an employee with employee id "employee.employee_id"
    |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
    |Aymat        |tata        |MS             |Male      |1988-02-28  |Employee  |QA           |

  @JsonWorkflow
  Scenario: create an employee via API work flow using json
    Given a request is prepared for creating an employee via json payload
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @Dyanmic
  Scenario: Create highly dynamic scenario
    Given a request is prepared for creating an employee with dynamic data "Denis", "sergey", "mike", "M", "2018-12-12", "Employee", "QA"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

   @update
   Scenario: Updating the employee using API call
     Given a request is prepared for updating the existing employee details
     When a PUT call is made to update the employee
     Then the status code for creating an employee should be 200


