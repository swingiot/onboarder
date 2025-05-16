package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.device.LicensedDevice;
import com.swingiot.onboarder.product.Product;
import com.swingiot.onboarder.product.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LicenceService {
  private final LicenceRepository licenceRepository;
  private final ProductsRepository productsRepository;

  public Licence createLicense(Licence licence) {
    Licence newLicence = Licence.builder()
        .devices(licence.getDevices())
        .productName(licence.getProductName())
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

  public LicensedDevice getLicenceFromMac(String mac) {
    Licence licence = licenceRepository.getLicenceByMacsIsContaining(Set.of(mac))
        .orElseThrow(() -> new AuthorizationException("Unregistered mac: " + mac));

    Product product = productsRepository.getProductByName(licence.getProductName())
        .orElseThrow(() -> new AuthorizationException("Unregistered mac: " + mac));

    return LicensedDevice.builder()
        .components(new HashSet<>(product.getComponents()))
        .mac(mac)
        .allocatedDate(Instant.now())
        .build();
  }

  public LicensedDevice registerDevice(String licenceKey, String mac) {
    Licence licence = licenceRepository.findByLicenceKey(licenceKey)
        .orElseThrow(() -> new AuthorizationException("Invalid licence key: " + licenceKey));

    Product product = productsRepository.getProductByName(licence.getProductName())
        .orElseThrow(() -> new AuthorizationException("Unregistered mac: " + mac));

    if (licence.getMacs().contains(mac)) {
      return LicensedDevice.builder()
          .components(new HashSet<>(product.getComponents()))
          .mac(mac)
          .allocatedDate(Instant.now())
          .build();
    }

    if (licence.getDevices() <= licence.getMacs().size()) {
      throw new AuthorizationException("Licence is already full. Permitted devices: " + licence.getDevices() + ", Registered devices: " + licence.getMacs().size());
    }

    licenceRepository.getLicenceByMacsIsContaining(Set.of(mac)).ifPresent(l -> {
      l.getMacs().remove(mac);
      licenceRepository.save(l);
    });

    licence.getMacs().add(mac);
    licenceRepository.save(licence);
    return LicensedDevice.builder()
        .components(new HashSet<>(product.getComponents()))
        .mac(mac)
        .allocatedDate(Instant.now())
        .build();
  }

}
