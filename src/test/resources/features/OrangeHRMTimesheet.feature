@regression @smoke

Feature: Validating select and edit add row timesheet functionalities

  Background: Repeated first steps i each scenario.
    Given user send username "Admin" and password "admin123"
    When user navigates to "My Timesheets" page

  @MB4-141
  Scenario: Validating select timesheet for week dropdown
    And user chooses and clicks one of the "weeks" weeks from dropdown
    Then user validates the desired "week" week is selected

  @MB4-145
  Scenario:  Validating edit add row timesheet functionality
    And user creates order with data
      | project name                                   | activity name | mon | tuesday | wed | thursday | friday | saturday | sunday |
      | Global Corp and Co - Global Software phase - 1 | Bug Fixes     | 8   | 5       | 5   | 8        | 5      | 0        | 0      |
    And user adds a row to the week
    Then user validates the row is added