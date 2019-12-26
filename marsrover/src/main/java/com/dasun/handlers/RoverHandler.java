package com.dasun.handlers;

import com.dasun.exceptions.CoordinatesUnavailableException;
import com.dasun.models.Plateau;
import com.dasun.models.Rover;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Eager singleton containing rover handling functions
 *
 */
public class RoverHandler {
    private static Logger log = Logger.getLogger(RoverHandler.class);
    private static final RoverHandler instance = new RoverHandler();
    private List<Rover> roversList = new ArrayList<Rover>();
    private static final String COORDINATES_UNAVAILABLE_ERROR = "Given coordinates are currently occupied by another rover";

    /**
     * Private constructor to restrict initialisation
     */
    private RoverHandler() {}

    /**
     * Returns singleton instance of RoverHandler class
     *
     * @return Singleton instance of RoverHandler class
     */
    public static RoverHandler getInstance() {
        return instance;
    }

    /**
     * Gets rovers list
     *
     * @return Roveres list
     */
    public List<Rover> getRoversList() {
        return roversList;
    }

    /**
     * Add a Rover object to rovers list and return the created Rover object
     *
     * @param x x coordinates of new rover
     * @param y y coordinates of new rover
     * @param orientation orientation of new rover
     * @return Newly initialised Rover object
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    public Rover addRover(int x, int y, String orientation) throws CoordinatesUnavailableException {
        this.handleCoordinatesAvailability(x, y);

        Rover rover = new Rover(x, y, orientation);
        roversList.add(rover);
        //Method will either throw a CoordinatesUnavailableException or return an initialised rover object
        return rover;
    }

    /**
     * Spin rover orientation 90 degrees left or right according to the given input
     *
     * @param rover Rover object
     * @param spinDirection Direction to spin the rover
     */
    public void spinRover(Rover rover, String spinDirection) {
        String roverCurrentOrientation = rover.getCurrentOrientation();
        if (spinDirection.equals("L")) {
            if (roverCurrentOrientation.equals("N")) {
                rover.setCurrentOrientation("W");
            } else if (roverCurrentOrientation.equals("S")) {
                rover.setCurrentOrientation("E");
            } else if (roverCurrentOrientation.equals("E")) {
                rover.setCurrentOrientation("N");
            } else if (roverCurrentOrientation.equals("W")) {
                rover.setCurrentOrientation("S");
            }
        } else if (spinDirection.equals("R")) {
            if (roverCurrentOrientation.equals("N")) {
                rover.setCurrentOrientation("E");
            } else if (roverCurrentOrientation.equals("S")) {
                rover.setCurrentOrientation("W");
            } else if (roverCurrentOrientation.equals("E")) {
                rover.setCurrentOrientation("S");
            } else if (roverCurrentOrientation.equals("W")) {
                rover.setCurrentOrientation("N");
            }
        }
    }

    /**
     * Update rover object coordinates one grid point forward from the current position
     *
     * @param rover Rover object
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    public void moveRover(Rover rover) throws CoordinatesUnavailableException {
        String roverCurrentOrientation = rover.getCurrentOrientation();
        int roverCurrentXCoordinates = rover.getCurrentXCoordinates();
        int roverCurrentYCoordinates = rover.getCurrentYCoordinates();

        if (roverCurrentOrientation.equals("N")) {
            this.handleCoordinatesAvailability(roverCurrentXCoordinates, roverCurrentYCoordinates + 1);
            rover.setCurrentYCoordinates(roverCurrentYCoordinates + 1);
        } else if (roverCurrentOrientation.equals("S")) {
            this.handleCoordinatesAvailability(roverCurrentXCoordinates, roverCurrentYCoordinates - 1);
            rover.setCurrentYCoordinates(roverCurrentYCoordinates - 1);
        } else if (roverCurrentOrientation.equals("E")) {
            this.handleCoordinatesAvailability(roverCurrentXCoordinates + 1, roverCurrentYCoordinates);
            rover.setCurrentXCoordinates(roverCurrentXCoordinates + 1);
        } else if (roverCurrentOrientation.equals("W")) {
            this.handleCoordinatesAvailability(roverCurrentXCoordinates - 1, roverCurrentYCoordinates);
            rover.setCurrentXCoordinates(roverCurrentXCoordinates - 1);
        }
    }

    /**
     * Checks if a set of coordinates are available
     * throws exception if coordinates are already taken or
     * out of boundaries on the plateau graph
     *
     * @param x x coordinates to check for availability
     * @param y y coordinates to check for availability
     * @throws CoordinatesUnavailableException throws exception when given set of coordinates are currently unavailable
     */
    private void handleCoordinatesAvailability(int x, int y) throws CoordinatesUnavailableException {
        //Check if given coordinates are out of boundaries
        if (x<0 || x>Plateau.getInstance().getSizeX() ||
            y<0 || y>Plateau.getInstance().getSizeY()) {

            //Inability for a rover to move to a given new position is assumed as an error
            //Check assumptions
            CoordinatesUnavailableException exception = new CoordinatesUnavailableException(COORDINATES_UNAVAILABLE_ERROR);
            log.error(COORDINATES_UNAVAILABLE_ERROR, exception);

            throw exception;
        }

        //Check if given coordinates are already taken
        int roverCurrentXCoordinates;
        int roverCurrentYCoordinates;
        for (Rover rover : roversList) {
            roverCurrentXCoordinates = rover.getCurrentXCoordinates();
            roverCurrentYCoordinates = rover.getCurrentYCoordinates();
            if (x == roverCurrentXCoordinates && y == roverCurrentYCoordinates) {
                //Inability for a rover to move to a given new position is assumed as an error
                //Check assumptions
                CoordinatesUnavailableException exception = new CoordinatesUnavailableException(COORDINATES_UNAVAILABLE_ERROR);
                log.error(COORDINATES_UNAVAILABLE_ERROR, exception);

                throw exception;
            }
        }
    }
}
