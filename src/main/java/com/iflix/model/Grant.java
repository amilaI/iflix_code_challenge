package com.iflix.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Grant implements Serializable {

    private long number;
    private Date date;
    private int period;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grant grant = (Grant) o;

        return number == grant.number;
    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + period;
        return result;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "number='" + number + '\'' +
                ", date=" + date +
                ", period=" + period +
                '}';
    }

}
