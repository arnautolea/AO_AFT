package org.myatf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
    private static final Logger logger = LogManager.getLogger(Logs.class);

    public static void main(String[] args) {
        logger.info("This is an informational message.");
        logger.error("This is an error message.");
    }
}

