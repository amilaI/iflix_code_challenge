package com.iflix;

import com.iflix.model.User;
import com.iflix.service.SubscriptionService;
import com.iflix.service.UserService;
import org.apache.log4j.Logger;
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

    /**
     * Main method
     *
     * @param args String[]
     */
    public static void main(String[] args) {

        // Create Objects of controllers
        UserService userService = new UserService();

        // Fetch user data
        List<User> userList = userService.getUsers();

        SubscriptionService subscriptionService = new SubscriptionService();

        // Create output array
        JSONObject outputJsonObject = subscriptionService.getSubscriptionJsonObject(userList);

        // Write data to file (/data/result.json)
        writeDataToOutputFile(outputJsonObject);

    }

}
