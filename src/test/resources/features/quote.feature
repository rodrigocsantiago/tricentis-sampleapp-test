Feature:  Get a vehicle insurance quote
  As a user
  I want to get vehicle insurance quotes using the data I fill in
  So that I can purchase vehicle insurance

  Scenario Outline: Send insurance quote by email
    Given I am at the <vehicleType> insurance quote page
    And I enter vehicle data with valid values
    And I enter insurant data with valid values
    And I enter product data with valid values
    And I select a price option
    When I send my user details
    Then I receive a success message
    Examples:
      | vehicleType |
      | Automobile |