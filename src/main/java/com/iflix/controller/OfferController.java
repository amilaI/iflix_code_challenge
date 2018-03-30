package com.iflix.controller;

import com.iflix.model.*;
import com.iflix.util.Constants;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class OfferController {

    private PartnerController partnerController;

    public OfferController() {
        partnerController = new PartnerController();
    }

    public UserSubscription getSubscriptionForUser(User user) {

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setName(user.getName());
        userSubscription.setNumber(user.getNumber());

        List<Grant> grantList = new ArrayList<>();
        List<Revocation> revocationList = new ArrayList<>();

        Partner amazecomPartner = partnerController.getPartnerData(Constants.PARTNER.AMAZECOM);

        List<Grant> amazecomPartnerGrants = amazecomPartner.getGrants();
        List<Revocation> amazecomPartnerRevocations = amazecomPartner.getRevocations();

        if (amazecomPartnerGrants != null && amazecomPartnerGrants.size() > 0) {
            for (Grant grant : amazecomPartnerGrants) {

                if (grant.getNumber() == user.getNumber()) {

                    grant.setPartner(Constants.PARTNER.AMAZECOM);
                    grant.setExpireDate(new DateTime().plusMonths(grant.getPeriod()).toDate());
                    grantList.add(grant);

                }

            }

        }

        if (amazecomPartnerRevocations != null && amazecomPartnerRevocations.size() > 0) {
            for (Revocation revocation : amazecomPartnerRevocations) {

                if (revocation.getNumber() == user.getNumber()) {
                    revocation.setPartner(Constants.PARTNER.AMAZECOM);
                    revocationList.add(revocation);
                }

            }

        }

        Partner wondertelPartner = partnerController.getPartnerData(Constants.PARTNER.WONDERTEL);


        List<Grant> wondertelPartnerGrants = wondertelPartner.getGrants();
        List<Revocation> wondertelPartnerRevocations = wondertelPartner.getRevocations();

        if (wondertelPartnerGrants != null && wondertelPartnerGrants.size() > 0) {
            for (Grant grant : wondertelPartnerGrants) {

                if (grant.getNumber() == user.getNumber()) {

                    grant.setPartner(Constants.PARTNER.WONDERTEL);
                    grant.setExpireDate(new DateTime().plusMonths(grant.getPeriod()).toDate());
                    grantList.add(grant);

                }

            }

        }

        if (wondertelPartnerRevocations != null && wondertelPartnerRevocations.size() > 0) {
            for (Revocation revocation : wondertelPartnerRevocations) {

                if (revocation.getNumber() == user.getNumber()) {
                    revocation.setPartner(Constants.PARTNER.WONDERTEL);
                    revocationList.add(revocation);
                }

            }

        }

        userSubscription.setGrantList(grantList);
        userSubscription.setRevocations(revocationList);
        return userSubscription;
    }


}
