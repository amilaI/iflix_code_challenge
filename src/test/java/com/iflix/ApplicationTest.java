package com.iflix;

import com.iflix.exception.InvalidPartnerException;
import com.iflix.service.SubscriptionService;
import com.iflix.service.UserService;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationTest {

    private static final String EXPECTED_OUTPUT = "{\"subscriptions\":[{\"John\":{\"wondertel\":90}},{\"Jenny\":{\"amazecom\":183}},{\"Ahmad\":{\"amazecom\":16,\"wondertel\":184}},{\"Hussain\":{\"amazecom\":59}},{\"Kumar\":{\"wondertel\":92}},{\"Farhan\":{\"wondertel\":89}},{\"Olga\":{\"wondertel\":89}},{\"Bridgette\":{\"amazecom\":92}},{\"Eyad\":{\"amazecom\":31}}]}";

    @Test
    public void testGetUsers() {

        UserService userService = new UserService();
        int size = userService.getUsers().size();
        Assert.assertEquals(size, 11);

    }

    @Test
    public void testGetSubscriptionJsonObject() {

        SubscriptionService subscriptionService = new SubscriptionService();
        JSONObject jsonObject = subscriptionService.getSubscriptionJsonObject(new UserService().getUsers());

        Assert.assertEquals(EXPECTED_OUTPUT, jsonObject.toString());

    }

    @Test
    public void testInvalidPartnerException() {

        InvalidPartnerException invalidPartnerException = new InvalidPartnerException();

        Assert.assertEquals(invalidPartnerException.getMessage(), "Invalid Partner!");

    }


}
