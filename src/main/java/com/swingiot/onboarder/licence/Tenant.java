package com.swingiot.onboarder.licence;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Tenant {
  private String tenantId;
  private String mqtt;
}
