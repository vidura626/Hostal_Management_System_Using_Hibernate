package lk.ijse.hostal.repository;

import org.hibernate.Session;

import java.util.List;

public interface CRUDUtil<T, ID> extends SuperRepo {
    boolean add(T obj, Session session);

    boolean update(T obj, Session session);

    boolean delete(ID id, Session session);

    T search(ID id, Session session);

    List<T> getAll(Session session);

    String generateNextId();
}
