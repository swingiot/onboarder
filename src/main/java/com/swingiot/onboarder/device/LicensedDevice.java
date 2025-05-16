package com.swingiot.onboarder.device;

import com.swingiot.onboarder.licence.Tenant;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder
@Jacksonized
public class LicensedDevice {
  private String mac;
  private Set<Component> components;
  private Instant allocatedDate;
  private Tenant tenant;
}
