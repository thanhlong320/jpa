package com.axonactive.jpa.config;

import com.axonactive.jpa.service.impl.DepartmentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);
    public static void main(String[] args) {
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.warn("WARN");
        logger.warn("WARN");
        logger.warn("WARN");
        logger.warn("WARN");

        logger.error("ERROR");
        logger.error("ERROR");
        logger.error("ERROR");
        logger.error("ERROR");
        logger.fatal("FATAL");
        logger.fatal("FATAL");
        logger.fatal("FATAL");
        logger.fatal("FATAL");

    }
}
