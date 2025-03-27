Feature: Return valid license

  Scenario: Correct key return valid license
    Given a valid license key
    And a mac
    When asked to register the device
    Then register device and return available components