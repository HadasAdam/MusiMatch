package com.example.musimatch.server.ejb;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.musimatch.models.MusimatchEntityInterface;
import com.example.musimatch.server.api.GeneralMusimatchDaoInterface;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.example.musimatch.services.ConnectionService;
import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GeneralMusimatchDaoEJB implements GeneralMusimatchDaoInterface, GeneralMusimatchDaoLocal {
    private static final String TAG = "GeneralMusimatchDaoEJB";
    private Connection connect = ConnectionService.instance.getConnection();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public <T extends MusimatchEntityInterface> List<T> findAll(Class<T> pType) {
        try {
            if (connect != null) {
                String query = "Select * from " + T.getTableName() + ";";
                Statement st = connect.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                return resultSetToList(resultSet);
            } else {
                Log.d("Connect", "can't connect to db - check connection");
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: ");
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public <T extends MusimatchEntityInterface> T findById(Class<T> pType, Long pId) {
        List<T> allObjects = findAll(pType);
        for(T object: allObjects)
        {
            if(object.getId().equals(pId))
                return object;
        }
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> T merge(T pEntityToMerge) {
        //TODO: CREATE A MERGE STATEMENT AND EXECUTE. DON'T FORGET TO CHECK IF THIS ID ALREADY EXISTS
        //                                  BECAUSE IF IT DOES EXIST - YOU SOULD UPDATE THE ENTITY.
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> List<T> mergeAll(Collection<T> pCollection) {
        List<T> list = Lists.newArrayList();
        for(T item: pCollection)
        {
            list.add(merge(item));
        }
        return list;
    }

    @Override
    public <T extends MusimatchEntityInterface> void remove(T pEntity) {
        //TODO: CREATE A REMOVE STATEMENT AND EXECUTE. DON'T FORGET TO CHECK IF THIS ENTITY ACTUALLY
        //                                EXISTS BY ITS ID - OTHERWISE, YOU DON'T HAVE TO DO A THING
    }

    @Override
    public <T extends MusimatchEntityInterface> void removeAllEntities(Collection<T> pCollection) {
        if (!pCollection.isEmpty())
        {
            for (T aPCollection: pCollection)
            {
                remove(aPCollection);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private static  <T extends MusimatchEntityInterface> List<T> resultSetToList(ResultSet resultSet) throws SQLException
    {
        List <T> list = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> resMap = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                resMap.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
            }
            list.add(T.create(resMap));
        }
        return list;
    }
}
