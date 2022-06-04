package com.example.musimatch.server.api;

import com.example.musimatch.models.MusimatchEntityInterface;

import java.util.Collection;
import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;

public interface GeneralMusimatchDaoInterface {
    //EntityManager getEntityManager();
    <T> List<T> findAll(Class<T> pType);
    <T extends MusimatchEntityInterface> T findById(Class<T> pType, Long pId);
    <T extends MusimatchEntityInterface> T merge(T pEntityToMerge);
    <T extends MusimatchEntityInterface> T safeMerge(T pEntityToMerge);
    <T extends MusimatchEntityInterface> List<T> mergeAll(Collection<T> pCollection);
    <T extends MusimatchEntityInterface> void remove(T pEntity);
    <T extends MusimatchEntityInterface> void removeAllEntities(Collection<T> pCollection);
    void flush();
}
