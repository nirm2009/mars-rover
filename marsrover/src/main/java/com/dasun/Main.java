package com.dasun;

import com.dasun.exceptions.CoordinatesUnavailableException;
import com.dasun.handlers.InputHandler;
import org.apache.log4j.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class);
    private static final String PROCESS_INPUT_ERROR = "Error occurred when processing console inputs";

    public static void main(String[] args) {
        try {
            InputHandler.getInstance().processInput();
        } catch (CoordinatesUnavailableException e) {
            log.error(PROCESS_INPUT_ERROR);
        }
    }
}
