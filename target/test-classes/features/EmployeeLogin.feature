Feature: Employee Login

  Scenario: Login with valid employee credentials

    Given the employee is on the login page
    When the employee enters username "employee1"
    And the employee enters password "Employee@123"
    And the employee clicks the Login button
    Then the employee should see the dashboard