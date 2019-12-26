package com.dasun.models;

/**
 * Eager singleton containing functions of a plateau
 *
 */
public class Plateau {
    private static final Plateau instance = new Plateau();
    private int sizeX;
    private int sizeY;

    /**
     * Private constructor to restrict initialisation
     */
    private Plateau() {}

    public static Plateau getInstance() {
        return instance;
    }

    /**
     * Get plateau size on x axis
     * @return Plateau size on x axis
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Set plateau size on x axis
     * @param sizeX Plateau size on x axis
     */
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    /**
     * Get plateau size on y axis
     * @return Plateau size on y axis
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Set plateau size on y axis
     * @param sizeY Plateau size on y axis
     */
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
