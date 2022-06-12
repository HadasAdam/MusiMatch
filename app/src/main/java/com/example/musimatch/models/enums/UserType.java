package com.example.musimatch.models.enums;

public enum UserType {
    NONE,
    POET,
    COMPOSER,
    POET_AND_COMPOSER;

    public static UserType getByName(String name)
    {
        if (name.equals(NONE.name()))
            return NONE;
        else if (name.equals(POET.name()))
            return POET;
        else if (name.equals(COMPOSER.name()))
            return COMPOSER;
        else return POET_AND_COMPOSER;
    }
}
