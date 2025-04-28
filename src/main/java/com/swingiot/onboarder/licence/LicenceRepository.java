package com.swingiot.onboarder.licence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LicenceRepository extends MongoRepository<Licence, String> {
  Optional<Licence> findByLicenceKey(String licenceKey);

  List<Licence> getLicenceByTenant_TenantId(String tenant);

  Optional<Licence> getLicenceByLicenceKey(String licenceKey);

  Optional<Licence> getLicenceByMacsIsContaining(Set<String> macs);
}
