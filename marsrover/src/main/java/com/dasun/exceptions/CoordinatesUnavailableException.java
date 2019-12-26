package com.dasun.exceptions;

/**
 * Custom exception thrown when a given set of coordinates are currently unavailable
 *
 */
public class CoordinatesUnavailableException extends Exception {
    public CoordinatesUnavailableException(String message) {
        super(message);
    }
}
