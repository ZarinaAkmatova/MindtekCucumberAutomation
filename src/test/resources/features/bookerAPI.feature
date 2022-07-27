Feature: Validating booking API calls

  @MB-12
  Scenario: Validate the API
  Given user create booking with random data
  Then user validates status code is 201
  When user gets created booking
  Then user validates status code is 200 and created data is returned
  When user updates booking
  Then user validates status code is 201
  When user deletes booking
  Then user validates status code is 204
  When user gets deleted data
  Then user validates status code is 404
