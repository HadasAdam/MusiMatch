package com.example.musimatch.services;

import com.example.musimatch.server.api.UserServiceInterface;
import com.example.musimatch.server.ejb.UserServiceEJB;

public class GeneralUtils {
    private static GeneralUtils instance = new GeneralUtils();

    public static Boolean StringToBoolean(String string) throws NullPointerException
    {
        if(string.equals("Y"))
            return true;
        else
            return false;
    }

    public static String BooleanToString(Boolean bool)
    {
        if (bool)
        {
            return "Y";
        }
        else
        {
            return "N";
        }
    }
}
