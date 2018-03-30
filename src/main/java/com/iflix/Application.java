package com.iflix;

import com.iflix.model.User;
import com.iflix.service.SubscriptionService;
import com.iflix.service.UserService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static com.iflix.util.DataWriter.writeDataToOutputFile;

/**
 * Application
 *
 * @author amilai (amila922@gmail.com)
 */
public class Application {

    private final static Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        // Create Objects of controllers
        UserService userService = new UserService();
        SubscriptionService subscriptionService = new SubscriptionService();

        // Fetch user data
        List<User> userList = userService.getUsers();

        // Create output array
        JSONObject outputJsonObject = new JSONObject();
        JSONArray subscriptionJson = new JSONArray();

        for (User user : userList) {

            // Calculate subscription
            JSONObject userSubscription = subscriptionService.doSubscriptionProcessing(user);

            if (userSubscription != null) {
                subscriptionJson.put(userSubscription);
            }

        }

        // Display final output
        outputJsonObject.put("subscriptions", subscriptionJson);

        // Write data to file (/data/result.json)
        writeDataToOutputFile(outputJsonObject);

    }

}
