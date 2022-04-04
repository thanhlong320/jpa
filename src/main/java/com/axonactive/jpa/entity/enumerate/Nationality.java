package com.axonactive.jpa.entity.enumerate;

public enum Nationality {
    VIETNAM(1),
    AMERICA(2),
    CHINA(3),
    RUSSIA(4);

    private Integer value;
    Nationality(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
