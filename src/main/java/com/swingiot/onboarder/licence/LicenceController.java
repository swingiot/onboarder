package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
