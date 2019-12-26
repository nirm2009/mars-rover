package com.dasun.models;

/**
 * Rover class containing functions of a rover
 *
 */
public class Rover {
    private int currentXCoordinates;
    private int currentYCoordinates;
    private String currentOrientation;

    /**
     * Private default constructor to restrict class initialisations
     * without position values
     */
    private Rover() {}

    /**
     * Constructor sets initial position values on the rover
     *
     * @param x Initial x coordinates
     * @param y Initial y coordinates
     * @param orientation Initial orientation
     */
    public Rover(int x, int y, String orientation) {
        currentXCoordinates = x;
        currentYCoordinates = y;
        this.currentOrientation = orientation;
    }

    /**
     * Gets rover current x coordinates
     * @return Rover current x coordinates
     */
    public int getCurrentXCoordinates() {
        return currentXCoordinates;
    }

    /**
     * Sets rover current x coordinates
     * @param currentXCoordinates current x coordination of the rover
     */
    public void setCurrentXCoordinates(int currentXCoordinates) {
        this.currentXCoordinates = currentXCoordinates;
    }

    /**
     * Gets rover current y coordinates
     * @return Rover current y coordinates
     */
    public int getCurrentYCoordinates() {
        return currentYCoordinates;
    }

    /**
     * Sets rover current y coordinates
     * @param currentYCoordinates current y coordination of the rover
     */
    public void setCurrentYCoordinates(int currentYCoordinates) {
        this.currentYCoordinates = currentYCoordinates;
    }

    /**
     * Gets rover current orientation
     * @return Rover current orientation
     */
    public String getCurrentOrientation() {
        return currentOrientation;
    }

    /**
     * Sets rover current orientation
     * @param currentOrientation Rover current orientation
     */
    public void setCurrentOrientation(String currentOrientation) {
        this.currentOrientation = currentOrientation;
    }
}
