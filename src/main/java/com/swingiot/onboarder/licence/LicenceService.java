package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.device.LicensedDevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LicenceService {
  private final LicenceRepository licenceRepository;

  public Licence createLicense(Licence licence) {
    Licence newLicence = Licence.builder()
        .devices(licence.getDevices())
        .components(licence.getComponents())
        .tenant(licence.getTenant())
        .licenceKey(UUID.randomUUID().toString())
        .macs(new HashSet<>())
        .createdDate(Instant.now())
        .modifiedDate(Instant.now())
        .build();
    return licenceRepository.save(newLicence);
  }

  public List<Licence> getLicences(String tenant) {
    return licenceRepository.getLicenceByTenant_TenantId(tenant);
  }

  public LicensedDevice registerDevice(String licenceKey, String mac) {
    Licence licence = licenceRepository.findByLicenceKey(licenceKey)
        .orElseThrow(() -> new AuthorizationException("Invalid licence key: " + licenceKey));
    if (licence.getDevices() <= licence.getMacs().size()) {
      throw new AuthorizationException("Licence is already full. Permitted devices: " + licence.getDevices() + ", Registered devices: " + licence.getMacs().size());
    }
    // remove mac from other licences
    // add mac to licence
    return LicensedDevice.builder().build();
  }
}
