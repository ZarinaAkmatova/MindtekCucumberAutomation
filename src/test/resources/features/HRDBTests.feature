@db @ui @regression @MB-105
Feature: Validating Database data is properly displayed in IU

  Scenario Outline: Validating Marketing department employees data in BD and IU
    When user navigates to HR App
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user selects "<Department>" department
    And user stores ui data in selected department
    Then user validates IU data matches with data in DB for "<Department>" department

    Examples:
    |Department|
    |Marketing |
    |IT        |
    |Administration|

    @MB4-192
    Scenario: Validating edit employee functionality
      When user navigates to HR App
      And user logs in with username "Mindtek" password "MindtekStudent"
      And user clicks on added button for employee with 100 id
      And user updates firstName for employee with "Patel"
      Then user validates that employee name is updated in UI
      And user validates employee name is updated with API get call


