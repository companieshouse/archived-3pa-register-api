package uk.gov.companieshouse.tpa.register.api.agent.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "codes")
public class AssuranceCode {

    @Id
    private String id;

    private String assuranceCode;

    private Instant startDate;

    private Instant endDate;

    public String getId() {
        return id;
    }

    public String getAssuranceCode() {
        return assuranceCode;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public AssuranceCode() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AssuranceCode that = (AssuranceCode) o;

        return new EqualsBuilder().append(getId(), that.getId()).append(getAssuranceCode(), that.getAssuranceCode())
                .append(getStartDate(), that.getStartDate()).append(getEndDate(), that.getEndDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getAssuranceCode()).append(getStartDate())
                .append(getEndDate()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", id)
                .append("assuranceCode", assuranceCode).append("startDate", startDate).append("endDate", endDate)
                .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<Consumer<AssuranceCode>> buildSteps;

        private Builder() {
            this.buildSteps = new ArrayList<>();
        }

        public Builder withAssuranceCode(String assuranceCode) {
            buildSteps.add(data -> data.assuranceCode = assuranceCode);
            return this;
        }

        public Builder withStartDate(Instant startDate) {
            buildSteps.add(data -> data.startDate = startDate);
            return this;
        }

        public Builder withEndDate(Instant endDate) {
            buildSteps.add(data -> data.endDate = endDate);
            return this;
        }

        public AssuranceCode build() {
            final AssuranceCode data = new AssuranceCode();
            buildSteps.forEach(step -> step.accept(data));
            return data;
        }
    }
}
