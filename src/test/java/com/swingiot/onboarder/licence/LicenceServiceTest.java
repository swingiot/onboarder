package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.device.Component;
import com.swingiot.onboarder.device.LicensedDevice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LicenceServiceTest {
  @Mock
  LicenceRepository licenceRepository;
  @InjectMocks
  LicenceService licenceService;

  @Test
  void createLicense_shouldCreateNewLicense() {
    Licence licence = Licence.builder().devices(10).components(Set.of(Component.SYSTEM, Component.CLASSIT)).build();
    Mockito.when(licenceRepository.save(Mockito.any(Licence.class))).thenReturn(licence);
    Licence actual = licenceService.createLicense(licence);
    assertThat(actual.getComponents()).isNotEmpty();
  }

  @Test
  void getLicences_shouldReturnLicencesBelongingToTenant() {
    String tenant = "TEST_TENANT";
    Licence licence = Licence.builder().devices(10).components(Set.of(Component.SYSTEM, Component.CLASSIT)).build();
    Mockito.when(licenceRepository.getLicenceByTenant_TenantId(tenant)).thenReturn(Collections.singletonList(licence));
    List<Licence> actual = licenceService.getLicences(tenant);
    assertThat(actual).isNotEmpty();
  }

  @Test
  void registerDevice_shouldReturnCorrectResponse() {
    String licenceKey = "L1C3NC3K3Y";
    String mac = "ec842bc5";
    Mockito.when(licenceRepository.findByLicenceKey(licenceKey)).thenReturn(Optional.of(Licence.builder().build()));

    LicensedDevice licensedDevice = licenceService.registerDevice(licenceKey, mac);
    assertThat(licensedDevice.getComponents()).isNotEmpty();
  }

  @Test
  void registerDevice_shouldReturnCorrectResponse_whenDeviceIsAlreadyRegisteredWithTheSameLicence() {


  }

  @Test
  void registerDevice_shouldThrowException_whenInvalidLicenceKey() {

  }

}
