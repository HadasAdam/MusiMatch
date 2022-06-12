package com.example.musimatch.models.enums;

import com.mysql.jdbc.StringUtils;

import java.util.HashMap;
import java.util.Map;

public enum PostType {
    POEM,
    MELODY;

    public static PostType getByName(String name)
    {
        if (name.equals(POEM.name()))
            return POEM;
        else return MELODY;
    }
}
