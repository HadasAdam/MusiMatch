package com.example.musimatch.models.enums;

public enum TagGroup {
    NONE,
    HAPPINESS,
    SADNESS,
    ROMANCE,
    THRILLER,
    KIDS,
    ANIMALS;

    public static TagGroup getByName(String name)
    {
        if (name.equals(NONE.name()))
            return NONE;
        else if (name.equals(HAPPINESS.name()))
            return HAPPINESS;
        else if (name.equals(SADNESS.name()))
            return SADNESS;
        else if (name.equals(ROMANCE.name()))
            return ROMANCE;
        else if (name.equals(THRILLER.name()))
            return THRILLER;
        else if (name.equals(KIDS.name()))
            return KIDS;
        else return ANIMALS;
    }
}
