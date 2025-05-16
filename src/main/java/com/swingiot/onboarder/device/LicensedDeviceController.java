package com.swingiot.onboarder.device;

import com.swingiot.onboarder.licence.LicenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/licences/devices")
@RequiredArgsConstructor
public class LicensedDeviceController {
  private final LicenceService licenceService;

  @PutMapping
  public LicensedDevice registerDevice(@RequestBody RegisterDevice registerDevice) {
    return licenceService.registerDevice(registerDevice.licenceKey(), registerDevice.mac());
  }

  @GetMapping("/{mac}")
  public LicensedDevice getLicence(@PathVariable String mac) {
    return licenceService.getLicenceFromMac(mac);
  }
}
