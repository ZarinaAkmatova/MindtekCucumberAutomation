@regression @OMT-7925
Feature: Validating Etsy search and filter functionality

    #Will run repeated steps that user has in each scenario
    #@Before method will run
  Background: Repeated first steps in each scenario
    Given user navigates to the Etsy application
    When user searches for "carpet"

  Scenario: Validating price range filter functionality for search items
    And user applies price filter over 1000
    Then user validates the items price is equal over 1000.00
     #@After method will run

  @OMT-7926
  Scenario: Validating search results
    Then user validates search result contain keyword "carpet" or "rug"