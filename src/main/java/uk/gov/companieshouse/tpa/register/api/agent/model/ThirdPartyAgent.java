package uk.gov.companieshouse.tpa.register.api.agent.model;

import com.mongodb.client.model.Collation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "3pas")
public class ThirdPartyAgent {

    @Id
    private String id;

    private String agentName;

    private String contactName;

    private String email;

    private Address address;

    private String supervisoryBody;

    private String amlRegistrationNumber;

    private String responsiblePersonName;

//    @DocumentReference(lazy = true, lookup = "{'assuranceCode' :?#{#target}}")
    @DocumentReference(lazy = true)
    private List<AssuranceCode> codes;

    public String getId() {
        return id;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getSupervisoryBody() {
        return supervisoryBody;
    }

    public String getAmlRegistrationNumber() {
        return amlRegistrationNumber;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public List<AssuranceCode> getCodes() {
        return codes;
    }

    public ThirdPartyAgent() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ThirdPartyAgent that = (ThirdPartyAgent) o;

        return new EqualsBuilder().append(getId(), that.getId()).append(getAgentName(), that.getAgentName())
                .append(getContactName(), that.getContactName()).append(getEmail(), that.getEmail())
                .append(getAddress(), that.getAddress()).append(getSupervisoryBody(), that.getSupervisoryBody())
                .append(getAmlRegistrationNumber(), that.getAmlRegistrationNumber())
                .append(getResponsiblePersonName(), that.getResponsiblePersonName()).append(getCodes(), that.getCodes())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getAgentName()).append(getContactName())
                .append(getEmail()).append(getAddress()).append(getSupervisoryBody()).append(getAmlRegistrationNumber())
                .append(getResponsiblePersonName()).append(getCodes()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", id)
                .append("agentName", agentName).append("contactName", contactName).append("email", email)
                .append("address", address).append("supervisoryBody", supervisoryBody)
                .append("amlRegistrationNumber", amlRegistrationNumber)
                .append("responsiblePersonName", responsiblePersonName).append("codes", codes).toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<Consumer<ThirdPartyAgent>> buildSteps;

        private Builder() {
            this.buildSteps = new ArrayList<>();
        }

        public Builder withAgentName(String agentName) {
            buildSteps.add(data -> data.agentName = agentName);
            return this;
        }

        public Builder withContactName(String contactName) {
            buildSteps.add(data -> data.contactName = contactName);
            return this;
        }

        public Builder withEmail(String email) {
            buildSteps.add(data -> data.email = email);
            return this;
        }

        public Builder withAddress(Address address) {
            buildSteps.add(data -> data.address = address);
            return this;
        }

        public Builder withSupervisoryBody(String supervisoryBody) {
            buildSteps.add(data -> data.supervisoryBody = supervisoryBody);
            return this;
        }

        public Builder withAmlRegistrationNumber(String amlRegistrationNumber) {
            buildSteps.add(data -> data.amlRegistrationNumber = amlRegistrationNumber);
            return this;
        }

        public Builder withResponsiblePersonName(String responsiblePersonName) {
            buildSteps.add(data -> data.responsiblePersonName = responsiblePersonName);
            return this;
        }

        public Builder withCodes(Collection<AssuranceCode> codes) {
            buildSteps.add(data -> data.codes = new ArrayList<>(codes) {
            });
            return this;
        }

        public ThirdPartyAgent build() {
            final ThirdPartyAgent data = new ThirdPartyAgent();
            buildSteps.forEach(step -> step.accept(data));
            return data;
        }
    }
}
