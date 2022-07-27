@smoke @regression
Feature: Validating HR App Rest API calls

  @api
  Scenario: Validating GET employee API call
    Given user sends GET call for employee with employeeId 150
    Then user validates status code is 200
    When user navigates to HR App
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user search for employee with employeeId 150
    Then validates user data from IU matches with API response

  @api @TC-101
  Scenario: Validating location API calls
    Given user creates location with POST api call with data
      | locationCity    | Orlando |
      | locationCountry | US      |
      | locationId      | 1932    |
      | locationState   | Florida |
    Then user validates status code is 201
    When user gets created location
    Then user validates status code is 200
    And user validates data in get call data matches with created data

  @api @TC-102
  Scenario: Validating employees data from specific department in UI matches with API response data
    When user navigates to HR App
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user selects "IT" department
    And user stores ui data in selected department
    When user sends get call employees api call for "IT" department
    Then user validates status code is 200
    And user validates ui data matches with api data in "IT" department employees

  @api @TC-104
  Scenario: Validating jobs api calls
    Given user creates job with POST job call with data
      | jobId  | Zarina |
      | salary | 100000 |
      | title  | SDET   |
    Then user validates status code is 201
    When user gets created job
    Then user validates status code is 200
    And user validates created data matches with data in get response

    @api @TC-105
      Scenario: Validating department name api calls
      Given user creates department with POST call with data
      |departmentName| Hello|
      |departmentId  | 123  |
      Then user validates status code is 201
      When user gets created department
      #Then user validates status code 200
      #And user validates created department matches with data in get call response







