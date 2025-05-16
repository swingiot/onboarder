package com.swingiot.onboarder.product;

import com.swingiot.onboarder.device.Component;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Builder
@Jacksonized
@Document
public class Product {
  @Id
  private String id;
  private String name;
  private String description;
  private List<Component> components;
}
