package com.example.musimatch.server.ejb;

import com.example.musimatch.models.MusimatchEntityInterface;
import com.example.musimatch.server.api.GeneralMusimatchDaoInterface;
import com.example.musimatch.server.api.GeneralMusimatchDaoLocal;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class GeneralMusimatchDaoEJB implements GeneralMusimatchDaoInterface, GeneralMusimatchDaoLocal {
    @Override
    public <T> List<T> findAll(Class<T> pType) {
        //TypedQuery lQuery = getEntityManager().createQuery("FROM " + pType.getSimpleName(), pType);
        //return lQuery.getResultsList();
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> T findById(Class<T> pType, Long pId) {
        //return getEntityManager().find(pType, pId);
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> T merge(T pEntityToMerge) {
        //return getEntityManager().merge(pEntityToMerge);
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> T safeMerge(T pEntityToMerge) {
//        if(getEntityManager().contains(pEntityToMerge))
//        {
//            return getEntityManager().merge(pEntityToMerge);
//        }
//        else
//        {
//            getEntityManager().persist(pEntityToMerge);
//        }
        return pEntityToMerge;
    }

    @Override
    public <T extends MusimatchEntityInterface> List<T> mergeAll(Collection<T> pCollection) {
        List<T> list = Lists.newArrayList();
        for(T item: pCollection)
        {
            list.add(merge(item));
        }
        //return list;
        return null;
    }

    @Override
    public <T extends MusimatchEntityInterface> void remove(T pEntity) {
        //pEntity = getEntityManager().merge(pEntity);
        //getEntityManager.remove(pEntity);
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

    @Override
    public void flush()
    {
        //getEntityManager().flush();
    }
}
