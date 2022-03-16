package com.axonactive.jpa.service;

public class AppConfigService {
    public static String getSecretKey(){
        return "long_nguyen";
    }

    public static String getIssuer(){
        return "";
    }

    public static Integer getTimeTolive(){
        return 100000;
    }
}
