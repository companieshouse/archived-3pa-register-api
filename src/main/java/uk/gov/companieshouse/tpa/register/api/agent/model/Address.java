package uk.gov.companieshouse.tpa.register.api.agent.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The agent address
 */
@Document
public final class Address {

    private String premise;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

    // required by Spring Data
    public Address() {
    }

    /**
     * The constructor for the address
     *
     * @param premise      the premise
     * @param addressLine1 the first line of the address
     * @param addressLine2 the second line of the address
     * @param town         the town
     * @param county       the county
     * @param country      the country
     * @param postcode     the postcode
     */
    public Address(final String premise, final String addressLine1, final String addressLine2, final String town,
                   final String county, final String country, final String postcode) {
        this.premise = premise;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(final String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Address address = (Address) o;

        return new EqualsBuilder().append(getPremise(), address.getPremise())
                .append(getAddressLine1(), address.getAddressLine1())
                .append(getAddressLine2(), address.getAddressLine2()).append(getTown(), address.getTown())
                .append(getCounty(), address.getCounty()).append(getCountry(), address.getCountry())
                .append(getPostcode(), address.getPostcode()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPremise()).append(getAddressLine1()).append(getAddressLine2())
                .append(getTown()).append(getCounty()).append(getCountry()).append(getPostcode()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("premise", premise)
                .append("addressLine1", addressLine1).append("addressLine2", addressLine2).append("town", town)
                .append("county", county).append("country", country).append("postcode", postcode).toString();
    }
}
