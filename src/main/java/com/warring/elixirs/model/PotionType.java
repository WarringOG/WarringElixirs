package com.warring.elixirs.model;

public enum PotionType {

    SPLASH,
    DRINK;

    public static PotionType fromString(String type) {
        if (type.equalsIgnoreCase("Drink")) {
            return PotionType.DRINK;
        }
        return PotionType.SPLASH;
    }
}
