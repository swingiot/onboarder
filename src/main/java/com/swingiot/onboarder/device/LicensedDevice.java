package com.swingiot.onboarder.device;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder
public class LicensedDevice {
  private String mac;
  private Set<Component> components;
  private Instant allocatedDate;
}
