Feature: Validating Bank Api calls

  @smoke @MB4--500
  Scenario: Validating post and get customer api calls
    Given user creates customer with data
      | accountOpenDate | 2020-01-01     |
      | active          | true           |
      | address         | 1415 Knox Road |
      | fullName        | Zarina         |
      | isActive        | true           |
    Then status code is 201
    When user gets created customer
    Then status code is 200
    And user validates get response data matches with created data

    @regression @MB4-501
  Scenario: Validating limit query parameter in customer api calls
    Given user creates multiple customers with data
      | accountOpenDate | active | address        | fullName        | isActive |
      | 2020-01-01      | true   | 129 Knox Road  | Patel Harsh     | true     |
      | 2020-01-01      | true   | 1785 Bell Road | Kim Yong        | true     |
      | 2020-01-01      | true   | 124 Java Road  | Priyanka Chopra | true     |
      | 2020-01-01      | true   | 1658 My Road   | John Doe        | true     |
      | 2020-01-01      | true   | 1985 Yong Road | Akon Patt       | true     |
    When user gets customer with limit 3
    Then user validates response has 3 customers

      @MB4-502
      Scenario Outline: Validating order query parameter in customer api calls
        Given user creates multiple customers with data
          | accountOpenDate | active | address        | fullName        | isActive |
          | 2020-01-01      | true   | 129 Knox Road  | Patel Harsh     | true     |
          | 2020-01-01      | true   | 1785 Bell Road | Kim Yong        | true     |
          | 2020-01-01      | true   | 124 Java Road  | Priyanka Chopra | true     |
          | 2020-01-01      | true   | 1658 My Road   | John Doe        | true     |
          | 2020-01-01      | true   | 1985 Yong Road | Akon Patt       | true     |
        When user gets customer with order "<order>"
        Then user validates response data in "<order>" order

        Examples:
        |order|
        |desc |
        |asc  |
