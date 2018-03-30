package com.iflix.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iflix.exception.InvalidPartnerException;
import com.iflix.model.Partner;
import com.iflix.util.Constants;
import org.apache.log4j.Logger;

import java.io.File;

public class PartnerService {

    private final static Logger LOGGER = Logger.getLogger(PartnerService.class);

    private String PROJECT_PATH;

    public PartnerService() {
        PROJECT_PATH = System.getProperty("user.dir");
    }

    /**
     * getPartnerData
     *
     * @param partnerName String
     * @return Partner
     */
    public Partner getPartnerData(String partnerName) {

        Partner partner = null;

        if (partnerName != null && !partnerName.isEmpty()) {
            try {

                String partnerDataLocation = getPartnerDataLocationByName(partnerName);

                ObjectMapper objectMapper = new ObjectMapper();

                partner = objectMapper.readValue(new File(PROJECT_PATH + partnerDataLocation), Partner.class);
                partner.setName(partnerName);
            } catch (Exception e) {

                LOGGER.error("Error occurred in PartnerService: getPartnerData() : Error[" + e + "]");

            }
        }


        return partner;

    }

    /**
     * getPartnerDataLocationByName
     *
     * @param partnerName String
     * @return String
     * @throws InvalidPartnerException e
     */
    private String getPartnerDataLocationByName(String partnerName) throws InvalidPartnerException {

        switch (partnerName) {
            case Constants.PARTNER.AMAZECOM:
                return Constants.PARTNER_PATH.AMAZECOM;
            case Constants.PARTNER.WONDERTEL:
                return Constants.PARTNER_PATH.WONDERTEL;
            default:
                throw new InvalidPartnerException();
        }

    }

}
