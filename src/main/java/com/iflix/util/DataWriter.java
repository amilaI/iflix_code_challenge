package com.iflix.util;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * DataWriter
 *
 * @author amilai (amila922@gmail.com)
 */
public class DataWriter {

    private final static Logger LOGGER = Logger.getLogger(DataWriter.class);

    /**
     * writeDataToOutputFile
     *
     * @param outputJsonObject JSONObject
     */
    public static void writeDataToOutputFile(JSONObject outputJsonObject) {

        try {

            try (FileWriter file = new FileWriter(System.getProperty("user.dir") + Constants.OUTPUT.FILE_LOCATION)) {

                file.write(outputJsonObject.toString());

                LOGGER.info("Results were successfully written to /data/result.json @" + new Date());

            }

        } catch (IOException e) {
            LOGGER.error("Error occurred in DataWriter: writeDataToOutput() : Error[" + e + "]");
        }

    }

}
