package com.dasun.utils;

import com.dasun.prop.CustomProperties;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Class holding console input validations
 *
 */
public class InputValidations {
    /**
     * Checks if a given input is a valid plateau size input
     *
     * @param input Plateau size input
     * @return Boolean indicating if a given input is a valid plateau size input
     */
    public static boolean isValidPlateauSizeInput(String input) {
        if (input == null) return false;

        try {
            String plateauSizeRegex = CustomProperties.getInstance().getPlateauSizeRegex();
            if (!Pattern.matches(plateauSizeRegex, input)) {
                return false;
            }
        } catch (IOException e) {
            //Logging is handled at CustomProperties class
            return false;
        }

        return true;
    }

    /**
     * Checks if a given input is a valid rover position input
     *
     * @param input Rover position input
     * @return Boolean indicating if a given input is a valid rover position input
     */
    public static boolean isValidRoverPositionInput(String input) {
        if (input == null) return false;

        try {
            String roverPosRegex = CustomProperties.getInstance().getRoverPosRegex();
            if (!Pattern.matches(roverPosRegex, input)) {
                return false;
            }
        } catch (IOException e) {
            //Logging is handled at CustomProperties class
            return false;
        }

        return true;
    }

    /**
     * Checks if a given input is a valid rover explore input
     *
     * @param input Rover explore input
     * @return Boolean indicating if a given input is a valid rover explore input
     */
    public static boolean isValidRoverExploreInput(String input) {
        if (input == null) return false;

        try {
            String roverExploreRegex = CustomProperties.getInstance().getRoverExploreRegex();
            if (!Pattern.matches(roverExploreRegex, input)) {
                return false;
            }
        } catch (IOException e) {
            //Logging is handled at CustomProperties class
            return false;
        }

        return true;
    }
}
