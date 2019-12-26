package com.dasun.handlers;

import com.dasun.exceptions.CoordinatesUnavailableException;
import com.dasun.models.Plateau;
import com.dasun.models.Rover;
import org.apache.log4j.Logger;
import com.dasun.utils.InputValidations;

import java.util.List;
import java.util.Scanner;

/**
 * Eager singleton handling console input functions
 *
 */
public class InputHandler {
    private static Logger log = Logger.getLogger(InputHandler.class);
    private static final InputHandler instance = new InputHandler();
    private static final Scanner inputScanner = new Scanner(System.in);
    private static final String TERMINATE_VALUE = "Q";

    /**
     * Private constructor to restrict initialisation
     */
    private InputHandler() {}

    /**
     * Returns singleton instance of InputHandler class
     *
     * @return singleton instance of InputHandler class
     */
    public static InputHandler getInstance() {
        return instance;
    }

    /**
     * Processes console input
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    public static void processInput() throws CoordinatesUnavailableException {
        processPlateauSizeInput();

        //Infinitely loop rover movement processes until a terminate value was input on the console
        while(true) {
            Rover rover = processRoverPositionInput();
            processRoverExploreInput(rover);
        }
    }

    /**
     * Process first line with plateau size input from console
     * @return Boolean indicating if plateau size input was successful
     */
    private static boolean processPlateauSizeInput() {
        log.info("Please input upper right coordinates of the plateau :");
        String plateauSize = inputScanner.nextLine();

        //Successfully exit program if a terminate value was input
        if (plateauSize.equals(TERMINATE_VALUE)) handleProgramExit();

        //Recursively loop plateau size input validation until a correct input is supplied
        while (!InputValidations.isValidPlateauSizeInput(plateauSize)) {
            log.info("Invalid plateau size input detected! Please try again");
            boolean successfulWithinRecursion = processPlateauSizeInput();
            //Return true from method if process was successful inside a recursion
            if (successfulWithinRecursion) return true;
        }

        //Set plateau size on models.Plateau singleton
        String[] plateauSizeArr = plateauSize.split(" ");
        Plateau.getInstance().setSizeX(Integer.parseInt(plateauSizeArr[0]));
        Plateau.getInstance().setSizeY(Integer.parseInt(plateauSizeArr[1]));

        return true;
    }

    /**
     * Process second line with rover position input from console
     *
     * @return Newly created Rover object
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    private static Rover processRoverPositionInput() throws CoordinatesUnavailableException {
        log.info("Please input position of the rover :");
        String roverPosition = inputScanner.nextLine();

        //Successfully exit program if a terminate value was input
        if (roverPosition.equals(TERMINATE_VALUE)) handleProgramExit();

        //Loop rover position input validation until a correct input is supplied
        while (!InputValidations.isValidRoverPositionInput(roverPosition)) {
            log.info("Invalid rover position input detected! Please try again");
            Rover rover = processRoverPositionInput();
            //Return rover created from a recursion
            if (rover != null) return rover;
        }

        String[] roverPositionArr = roverPosition.split(" ");

        return RoverHandler.getInstance().addRover(Integer.parseInt(roverPositionArr[0]),
                                                   Integer.parseInt(roverPositionArr[1]),
                                                   roverPositionArr[2]);
    }

    /**
     * Process third line with rover explore input from console
     *
     * @param rover Rover object
     * @return Boolean indicating if rover explore input was successful
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    private static boolean processRoverExploreInput(Rover rover) throws CoordinatesUnavailableException {
        log.info("Please input explore instructions for the rover :");
        String roverExplore = inputScanner.nextLine();

        //Successfully exit program if a terminate value was input
        if (roverExplore.equals(TERMINATE_VALUE)) handleProgramExit();

        //Loop rover explore input validation until a correct input is supplied
        while (!InputValidations.isValidRoverExploreInput(roverExplore)) {
            log.info("Invalid rover explore input detected! Please try again");
            boolean successfulWithinRecursion = processRoverExploreInput(rover);
            //Return true from method if process was successful inside a recursion
            if (successfulWithinRecursion) return true;
        }

        for (int i=0; i<roverExplore.length(); i++) {
            String currentExploreInstruction = roverExplore.substring(i, i+1);
            if (currentExploreInstruction.equals("M")) {
                RoverHandler.getInstance().moveRover(rover);
            }
            //roverExplore would be L or R at this level

            else {
                RoverHandler.getInstance().spinRover(rover, currentExploreInstruction);
            }
        }

        return true;
    }

    /**
     * Prints final coordinates of all the rovers
     */
    private static void printRoverFinalCoordinates() {
        List<Rover> roversList = RoverHandler.getInstance().getRoversList();
        for (Rover rover : roversList) {
            log.info(rover.getCurrentXCoordinates() + " " + rover.getCurrentYCoordinates() + " " + rover.getCurrentOrientation());
        }
    }

    /**
     * Handles program exit
     */
    private static void handleProgramExit() {
        log.info("Exiting program");
        inputScanner.close();
        printRoverFinalCoordinates();
        System.exit(0);
    }
}
