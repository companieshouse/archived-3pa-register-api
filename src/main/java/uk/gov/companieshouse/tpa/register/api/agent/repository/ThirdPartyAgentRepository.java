package uk.gov.companieshouse.tpa.register.api.agent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.gov.companieshouse.tpa.register.api.agent.model.ThirdPartyAgent;

@RepositoryRestResource(path = "3pas", collectionResourceRel = "codes")
public interface ThirdPartyAgentRepository extends MongoRepository<ThirdPartyAgent, String> {
}