package org.example.loggerConfig;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class MyLogger {
    private static final String FILE_NAME = "resources/cash_register.properties";


    public static Logger getLogger(String className) {
        final Logger logger = MyLogger.getLogger(className);
        PropertyConfigurator.configure(FILE_NAME);
        return logger;
    }

    public static String message(String message){
        return message;
    }

    public static String exceptionMessage(Exception e) {
        return e.getMessage() + Arrays.toString(e.getStackTrace());
    }
}
