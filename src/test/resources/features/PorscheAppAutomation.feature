@smoke @PO-1234
Feature: Validating Porsche App Car Price


  Scenario: Validating Porsche Price
    Given user navigates to Porsche app
    When user stores the price and selects the model 718 Cayman
    Then user validates Base price is matched with listed price
