package com.axonactive.jpa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    public class Token {
        private String token;
        private Integer timeToLive;
    }
