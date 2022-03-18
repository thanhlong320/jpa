package com.axonactive.jpa.service;

import java.util.ResourceBundle;

    public class AppConfigService {
        private static final ResourceBundle rb = ResourceBundle.getBundle("jwt");
        private static final Integer TIME_TO_LIVE_MILLISECONDS = Integer.valueOf(rb.getString("jwt.time-to-live"));
        private static final Integer MILLISECONDS_PER_MINUTES = 60 * 1000;

        public static String getSecretKey(){
            return rb.getString("jwt.secret.key");
        }

        public static String getIssuer(){
            return rb.getString("jwt.issuer");
        }

        public static Integer getTimeToLive(){
            return TIME_TO_LIVE_MILLISECONDS;
        }

        public static Integer getTimeToLiveAsMinutes(){
            return TIME_TO_LIVE_MILLISECONDS / MILLISECONDS_PER_MINUTES;
        }
    }
