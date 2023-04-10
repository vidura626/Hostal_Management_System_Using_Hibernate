package lk.ijse.hostal.repository;

import org.hibernate.Session;

import java.util.List;

public interface CRUDUtil<T, ID> extends SuperRepo {
    ID add(T obj, Session session) throws Exception;

    void update(T obj, Session session) throws Exception;

    void delete(ID id, Session session) throws Exception;

    T search(ID id, Session session);

    List<T> getAll(Session session);

    String generateNextId(Session session) throws Exception;
}
