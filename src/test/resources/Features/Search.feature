Feature: Image URL Extraction

  @DuckDuckGo
  Scenario Outline: List all images URL with removing DuckDuckGo URL part
    Given User is on the DuckDuckGo search page
    When User performs a search for "<searchText>"
    Then Navigate to "Images" section
    And List all the URL of images without DuckDuckGo url part

    Examples:
      | searchText |
      | Nice Cars  |