package com.iflix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {

    private String name;
    private List<Grant> grants;
    private List<Revocation> revocations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grant> getGrants() {
        return grants;
    }

    public void setGrants(List<Grant> grants) {
        this.grants = grants;
    }

    public List<Revocation> getRevocations() {
        return revocations;
    }

    public void setRevocations(List<Revocation> revocations) {
        this.revocations = revocations;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Partner partner = (Partner) o;

        return name != null ? name.equals(partner.name) : partner.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (grants != null ? grants.hashCode() : 0);
        result = 31 * result + (revocations != null ? revocations.hashCode() : 0);
        return result;
    }
}
