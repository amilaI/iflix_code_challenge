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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSubscription that = (UserSubscription) o;

        if (number != that.number) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (grantList != null ? !grantList.equals(that.grantList) : that.grantList != null) return false;
        return revocations != null ? revocations.equals(that.revocations) : that.revocations == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (number ^ (number >>> 32));
        result = 31 * result + (grantList != null ? grantList.hashCode() : 0);
        result = 31 * result + (revocations != null ? revocations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSubscription{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", grantList=" + grantList +
                ", revocations=" + revocations +
                '}';
    }
}
