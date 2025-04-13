package com.swingiot.onboarder.licence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenceRepository extends MongoRepository<Licence, String> {
  Optional<Licence> findByLicenceKey(String licenceKey);

  List<Licence> getLicenceByTenant_TenantId(String tenantTenantId);
}
