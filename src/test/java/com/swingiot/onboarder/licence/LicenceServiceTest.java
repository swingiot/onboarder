package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.device.Component;
import com.swingiot.onboarder.device.LicensedDevice;
import com.swingiot.onboarder.product.Product;
import com.swingiot.onboarder.product.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LicenceServiceTest {
  @Mock
  LicenceRepository licenceRepository;
  @Mock
  ProductsRepository productsRepository;
  @InjectMocks
  LicenceService licenceService;

  @Test
  void createLicense_shouldCreateNewLicense() {
    Licence licence = Licence.builder().devices(10).productName("CLASSIT").build();
    Mockito.when(licenceRepository.save(Mockito.any(Licence.class))).thenReturn(licence);
    Licence actual = licenceService.createLicense(licence);
    assertThat(actual).isNotNull();
  }

  @Test
  void getLicences_shouldReturnLicencesBelongingToTenant() {
    String tenant = "TEST_TENANT";
    Licence licence = Licence.builder().devices(10).productName("CLASSIT").build();
    Mockito.when(licenceRepository.getLicenceByTenant_TenantId(tenant)).thenReturn(Collections.singletonList(licence));
    List<Licence> actual = licenceService.getLicences(tenant);
    assertThat(actual).isNotEmpty();
  }

  @Test
  void registerDevice_shouldReturnCorrectResponse() {
    String licenceKey = "L1C3NC3K3Y";
    String mac = "ec842bc5";
    String productName = "CLASSIT";
    Licence licence = Licence.builder().devices(10).productName(productName).macs(Set.of(mac)).build();
    Product product = Product.builder().components(Collections.singletonList(Component.CLASSIT)).build();
    Mockito.when(licenceRepository.findByLicenceKey(licenceKey)).thenReturn(Optional.of(licence));
    Mockito.when(productsRepository.getProductByName(productName)).thenReturn(Optional.of(product));

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
