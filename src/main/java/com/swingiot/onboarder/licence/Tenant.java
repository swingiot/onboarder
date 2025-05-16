package com.swingiot.onboarder.licence;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class Tenant {
  private String tenantId;
  private String mqtt;
}
