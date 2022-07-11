package uk.gov.companieshouse.tpa.register.api.agent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.gov.companieshouse.tpa.register.api.agent.model.AssuranceCode;

@RepositoryRestResource(path = "codes")
public interface AssuranceCodeRepository extends MongoRepository<AssuranceCode, String> {
}
