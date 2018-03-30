package com.iflix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iflix.model.User;
import com.iflix.model.UserList;
import com.iflix.util.Constants;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class UserController {

    private final static Logger LOGGER = Logger.getLogger(PartnerController.class);

    private String PROJECT_PATH;

    public UserController() {
        PROJECT_PATH= System.getProperty("user.dir");
    }

    public List<User> getUsers() {

        List<User> userList = null;

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            UserList users = objectMapper.readValue(new File(PROJECT_PATH + Constants.USER.DATA_LOCATION), UserList.class);

            if (users != null && users.getUsers() != null) {
                userList = users.getUsers();
            }

        } catch (IOException e) {
            LOGGER.error("Error occurred in UserController: getUsers() : Error[" + e + "]");

        }

        return userList;

    }


}
