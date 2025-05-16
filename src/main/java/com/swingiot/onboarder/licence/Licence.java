package com.swingiot.onboarder.licence;

import com.swingiot.onboarder.device.Component;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document
@Getter
@Builder
public class Licence {
  @Id
  private String id;
  @Indexed
  private String licenceKey;
  private int devices;
  private Tenant tenant;
  private String productName;
  private Set<String> macs;
  private Instant createdDate;
  private Instant modifiedDate;
}
