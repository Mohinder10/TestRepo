Feature: Test Application scenarios

  @Test
  Scenario Outline: Test case one
    Given Open browser and start the application
    And user clicks on "Contact" link
    And user clicks on "Submit" link
    And user validates errors
      | Field    | Error                |
      | Forename | Forename is required |
      | Email    | Email is required    |
      | Message  | Message is required  |
    And user populates mandatory fields
      | Forename | Email                     | Message      |
      | Mohinder | mohinderbatra@hotmail.com | Hello World! |
    Then user validates all errors are gone

  @Test
  Scenario Outline: Test case two
    Given Open browser and start the application
    And user clicks on "Contact" link
    And user populates mandatory fields
      | Forename | Email                     | Message      |
      | Mohinder | mohinderbatra@hotmail.com | Hello World! |
    And user clicks on "Submit" link
    Then user validates successful submission message
      | Forename |
      | Mohinder |

  @Test
  Scenario Outline: Test case three
    Given Open browser and start the application
    And user clicks on "Contact" link
    And user populates mandatory fields
      | Forename | Email | Message |
      | @@       | @@    | @@      |
    And user validates errors
      | Field | Error                      |
      | Email | Please enter a valid email |

  @Test
  Scenario Outline: Test case four
    Given Open browser and start the application
    And user clicks on "Shop" link
    And user buys item
      | ItemName     | Qty |
      | Funny Cow    |   2 |
      | Fluffy Bunny |   1 |
    And user clicks on cart menu
    Then user validates items are in the cart
      | ItemName     | Qty |
      | Funny Cow    |   2 |
      | Fluffy Bunny |   1 |
