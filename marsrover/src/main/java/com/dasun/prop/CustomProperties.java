package com.dasun.prop;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lazy singleton custom properties class containing project properties
 *
 */
public class CustomProperties extends java.util.Properties {
    private static Logger log = Logger.getLogger(CustomProperties.class);
    private static CustomProperties properties = null;
    private static final String PROPERTIES_PATH = "config.properties";
    private static final String PLATEAU_SIZE_REGEX_PROP_NAME = "plateau_size_regex";
    private static final String ROVER_POS_REGEX_PROP_NAME = "rover_position_regex";
    private static final String ROVER_EXPLORE_PROP_NAME = "rover_explore_regex";

    /**
     * Private constructor to restrict initialisation
     */
    private CustomProperties() {}

    /**
     * Returns singleton instance of CustomProperties class
     *
     * @return Singleton instance of CustomProperties class
     * @throws IOException Throws exception when an error occurred when loading properties
     */
    public static CustomProperties getInstance() throws IOException {
        if (properties == null) {
            properties = new CustomProperties();
            loadProperties();
        }

        return properties;
    }

    /**
     * Gets plateau size regex string
     *
     * @return Plateau size regex string
     */
    public String getPlateauSizeRegex() {
        return properties.getProperty(PLATEAU_SIZE_REGEX_PROP_NAME);
    }

    /**
     * Gets rover pos regex string
     *
     * @return Rover pos regex string
     */
    public String getRoverPosRegex() {
        return properties.getProperty(ROVER_POS_REGEX_PROP_NAME);
    }

    /**
     * Gets rover explore regex string
     *
     * @return Rover explore regex string
     */
    public String getRoverExploreRegex() {
        return properties.getProperty(ROVER_EXPLORE_PROP_NAME);
    }

    /**
     * reads properties file and loads it
     *
     * @throws IOException Throws exception when an error occurred when loading properties
     */
    private static void loadProperties() throws IOException {
        try {
            InputStream inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
        } catch (IOException ioe) {
            log.error("Loading properties file failed", ioe);
            throw ioe;
        }
    }
}
