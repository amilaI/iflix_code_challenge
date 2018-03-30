package com.iflix;

import com.iflix.controller.PartnerController;
import com.iflix.controller.UserController;
import com.iflix.model.Partner;
import com.iflix.model.User;
import com.iflix.util.Constants;
import org.apache.log4j.Logger;

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

        System.out.println();


    }

}
