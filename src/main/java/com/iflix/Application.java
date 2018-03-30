package com.iflix;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.iflix.controller.OfferController;
import com.iflix.controller.PartnerController;
import com.iflix.controller.UserController;
import com.iflix.model.Partner;
import com.iflix.model.User;
import com.iflix.model.UserSubscription;
import com.iflix.util.Constants;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * This is the main class of this program
 */
public class Application {

    private final static Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        /*UserController userController = new UserController();

        List<User> userList = userController.getUsers();

        for (User user: userList){
            System.out.println(user.getNumber() + user.getNumber());
        }*/

        /*PartnerController partnerController = new PartnerController();

        Partner partner = partnerController.getPartnerData(Constants.PARTNER.WONDERTEL);*/


        User user = new User();
        user.setName("Ahmad");
        user.setNumber(99994739873l);

        /*JSONObject partners = new JSONObject();
        partners.put("amazecom", 34);
        partners.put("wondertel", 62);

        JSONObject user1 = new JSONObject();
        user1.put("Sally",partners);

        JSONObject user2 = new JSONObject();
        user2.put("Farhan",partners);

        JSONArray subscriptionJson = new JSONArray();
        subscriptionJson.put(user1);
        subscriptionJson.put(user2);

        JSONObject outputJsonObject = new JSONObject();
        outputJsonObject.put("subscriptions", subscriptionJson);*/

        OfferController offerController = new OfferController();
        UserSubscription userSubscription= offerController.getSubscriptionForUser(user);

        System.out.println();







    }

}
