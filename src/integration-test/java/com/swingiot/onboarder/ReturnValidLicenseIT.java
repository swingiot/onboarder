package com.swingiot.onboarder;

import com.swingiot.onboarder.device.Component;
import com.swingiot.onboarder.device.LicensedDevice;
import com.swingiot.onboarder.licence.LicenceRepository;
import com.swingiot.onboarder.licence.LicenceService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class ReturnValidLicenseIT {
  private String licenceKey;
  private String mac;
  private LicensedDevice licensedDevice;

  private LicenceRepository licenceRepository;

  @Given("a valid license key {string}")
  public void a_valid_license_key(String licenceKey) {
    this.licenceKey = licenceKey;
  }
  @And("a mac {string}")
  public void a_mac(String mac) {
    this.mac = mac;
  }
  @When("asked to register the device")
  public void asked_to_register_the_device() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
    licensedDevice = new LicenceService(licenceRepository).registerDevice(licenceKey, mac);
  }
  @Then("register device and return available components")
  public void register_device_and_return_available_components() {
    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
    assertThat(licensedDevice.getComponents()).contains(Component.CLASSIT);
  }


}
