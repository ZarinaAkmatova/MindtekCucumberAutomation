Feature: Validating select and edit add row timesheet functionalities

  Background: Repeated first steps i each scenario.

    Given user send username "Admin" and password "admin123"
    When user navigates to "My Timesheets" page

  @MB4-170
  Scenario Outline:
    And user navigates to users page
    Then user searches for valid user with data


      #After method is invalid

    Then user searches for invalid user
      | Username   | User Role   | Employee Name   | Status   |
      | <Username> | <User Role> | <Employee Name> | <Status> |
    And validate user is shown

    Examples:
      | Username    | User Role | Employee Name | Status  |
      | Aaliyah.Haq | ESS       | Admin A       | Enabled |

    And validates error "No record found" message




