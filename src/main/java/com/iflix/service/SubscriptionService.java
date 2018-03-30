package com.iflix.service;

import com.iflix.model.*;
import com.iflix.util.Constants;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * SubscriptionService
 *
 * @author amilai (amila922@gmail.com)
 */
public class SubscriptionService {

    private PartnerService partnerService;

    public SubscriptionService() {
        partnerService = new PartnerService();
    }

    /**
     * getSubscriptionForUser
     *
     * @param user User
     * @return UserSubscription
     */
    private UserSubscription getSubscriptionForUser(User user) {

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setName(user.getName());
        userSubscription.setNumber(user.getNumber());

        List<Grant> grantList = new ArrayList<>();
        List<Revocation> revocationList = new ArrayList<>();

        // Get amazecom data
        Partner amazecomPartner = partnerService.getPartnerData(Constants.PARTNER.AMAZECOM);

        List<Grant> amazecomPartnerGrants = amazecomPartner.getGrants();
        List<Revocation> amazecomPartnerRevocations = amazecomPartner.getRevocations();

        if (amazecomPartnerGrants != null && amazecomPartnerGrants.size() > 0) {
            for (Grant grant : amazecomPartnerGrants) {

                if (grant.getNumber() == user.getNumber() && grant.getPeriod() > 0) {

                    grant.setPartner(Constants.PARTNER.AMAZECOM);
                    grant.setExpireDate(new DateTime(grant.getDate()).plusMonths(grant.getPeriod()).toDate());
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

        // Get wondertel data
        Partner wondertelPartner = partnerService.getPartnerData(Constants.PARTNER.WONDERTEL);


        List<Grant> wondertelPartnerGrants = wondertelPartner.getGrants();
        List<Revocation> wondertelPartnerRevocations = wondertelPartner.getRevocations();

        if (wondertelPartnerGrants != null && wondertelPartnerGrants.size() > 0) {
            for (Grant grant : wondertelPartnerGrants) {

                if (grant.getNumber() == user.getNumber()) {

                    grant.setPartner(Constants.PARTNER.WONDERTEL);
                    grant.setExpireDate(new DateTime(grant.getDate()).plusMonths(grant.getPeriod()).toDate());
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

    /**
     * doSubscriptionProcessing
     *
     * @param user User
     * @return JSONObject
     */
    public JSONObject doSubscriptionProcessing(User user) {

        SubscriptionService offerController = new SubscriptionService();
        UserSubscription userSubscription = offerController.getSubscriptionForUser(user);

        List<Grant> grantList = userSubscription.getGrantList();
        List<Revocation> revocationList = userSubscription.getRevocations();

        // Sort using the grant start date
        grantList.sort((o1, o2) -> {

            if (o1.getDate().before(o2.getDate())) {
                return -1;
            } else if (o1.getDate().after(o2.getDate())) {
                return 1;
            } else {
                return 0;
            }
        });

        // Sort using the revocate date
        revocationList.sort((o1, o2) -> {

            if (o1.getDate().before(o2.getDate())) {
                return -1;
            } else if (o1.getDate().after(o2.getDate())) {
                return 1;
            } else {
                return 0;
            }
        });

        // Rule: The first partner to give a GRANT "owns" that user. Other partners cannot add to that users Offer.
        JSONObject partners = null;

        if (grantList.size() > 0) {

            Grant finalGrant = grantList.get(0);
            String firstPartner = finalGrant.getPartner();
            String secondPartner = null;
            int firstPartnerDays = Days.daysBetween(new DateTime(finalGrant.getDate()), new DateTime(finalGrant.getExpireDate())).getDays();
            int secondPartnerDays = 0;

            for (Revocation revocation : revocationList) {
                // Rule: A partner can only revoke an Offer if they "own" the user.
                if (revocation.getPartner().equals(firstPartner)) {

                    if (revocation.getDate().before(finalGrant.getExpireDate())) {

                        firstPartnerDays = Days.daysBetween(new DateTime(finalGrant.getDate()), new DateTime(revocation.getDate())).getDays();

                        for (Grant grant : grantList) {
                            if (grant.getDate().after(revocation.getDate())) {
                                if (!grant.getPartner().equals(firstPartner)) {

                                    secondPartner = grant.getPartner();
                                    secondPartnerDays = Days.daysBetween(new DateTime(grant.getDate()), new DateTime(grant.getExpireDate())).getDays();

                                    break;
                                }

                            }

                        }

                        break;

                    }

                }
            }

            partners = new JSONObject();

            partners.put(firstPartner, firstPartnerDays);

            if (secondPartner != null && secondPartnerDays > 0) {
                partners.put(secondPartner, secondPartnerDays);
            }

            JSONObject user1 = new JSONObject();
            user1.put(user.getName(), partners);

            return user1;

        } else {
            return null;
        }

    }

}
