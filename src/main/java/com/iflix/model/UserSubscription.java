package com.iflix.model;

import java.util.List;

public class UserSubscription {

    private String name;
    private long number;
    private List<Grant> grantList;
    private List<Revocation> revocations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public List<Grant> getGrantList() {
        return grantList;
    }

    public void setGrantList(List<Grant> grantList) {
        this.grantList = grantList;
    }

    public List<Revocation> getRevocations() {
        return revocations;
    }

    public void setRevocations(List<Revocation> revocations) {
        this.revocations = revocations;
    }
}
