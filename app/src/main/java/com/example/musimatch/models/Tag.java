package com.example.musimatch.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;

import com.example.musimatch.models.enums.TagGroup;
import com.example.musimatch.models.enums.UserType;
import com.example.musimatch.services.GeneralUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Tag implements MusimatchEntityInterface {
    public final static String TABLE_NAME = "TAGS";

    private static final String ID_COLUMN_NAME = "ID";
    private static final String TAG_GROUP_COLUMN_NAME = "TAG_GROUP";
    private static final String NAME_COLUMN_NAME = "NAME";

    private Long id;
    private TagGroup tagGroup = TagGroup.NONE;
    private String name;

    public Tag(){}

    public Tag(Long id, TagGroup tagGroup, String name) {
        this.id = id;
        this.tagGroup = tagGroup;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public TagGroup getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroup tagGroup) {
        this.tagGroup = tagGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put(ID_COLUMN_NAME, id);
        json.put(TAG_GROUP_COLUMN_NAME, tagGroup.name());
        json.put(NAME_COLUMN_NAME, name);
        return json;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Tag create(Map<String, Object> json) {
        try {
            Long id = Long.parseLong((String) json.get(ID_COLUMN_NAME));
            TagGroup tagGroup = TagGroup.getByName((String) json.get(TAG_GROUP_COLUMN_NAME));
            String name = (String) json.get(NAME_COLUMN_NAME);

            return new Tag(id, tagGroup, name);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTableName() {
        return TABLE_NAME;
    }
}
