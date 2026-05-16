package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/licences")
@RequiredArgsConstructor
public class LicenceController {
  private final LicenceService licenceService;

  @PostMapping
  public Licence createLicence(@RequestBody Licence licence) {
    return licenceService.createLicense(licence);
  }

  @GetMapping
  public List<Licence> getLicences(@RequestParam("tenant") String tenantId) {
    if (tenantId == null || tenantId.isEmpty()) {
      throw new InvalidRequestException("Tenant id could not be null or empty");
    }
    return licenceService.getLicences(tenantId);
  }

  @PatchMapping("/{id}")
  public Licence updateLicence(@PathVariable String id, @RequestBody Map<String, Integer> body) {
    return licenceService.updateDeviceCount(id, body.get("devices"));
  }
}
