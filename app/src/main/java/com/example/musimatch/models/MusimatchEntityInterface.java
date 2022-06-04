package com.example.musimatch.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import java.io.Serializable;
import java.util.Map;


public interface MusimatchEntityInterface extends Serializable {
    Long getId();
    void setId(Long id);
    static String getTableName()
    {
        return "NO_TABLE_NAME";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static <T extends MusimatchEntityInterface> T create(Map<String, Object> json){
        return null;
    }
}
