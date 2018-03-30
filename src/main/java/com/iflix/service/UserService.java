package com.iflix.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iflix.model.User;
import com.iflix.model.UserList;
import com.iflix.util.Constants;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * UserService
 *
 * @author amilai (amila922@gmail.com)
 */
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService.class);

    private String PROJECT_PATH;

    // Constructor
    public UserService() {
        PROJECT_PATH = System.getProperty("user.dir");
    }

    /**
     * getUsers
     *
     * @return List<User>
     */
    public List<User> getUsers() {

        List<User> userList = null;

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            UserList users = objectMapper.readValue(new File(PROJECT_PATH + Constants.USER.DATA_LOCATION), UserList.class);

            if (users != null && users.getUsers() != null) {
                userList = users.getUsers();
            }

        } catch (IOException e) {
            LOGGER.error("Error occurred in UserService: getUsers() : Error[" + e + "]");

        }

        return userList;

    }

}
