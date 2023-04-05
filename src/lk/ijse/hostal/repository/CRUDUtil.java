package lk.ijse.hostal.repository;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface CRUDUtil<T, ID> extends SuperRepo {
    ID add(T obj, Session session);

    boolean update(T obj, Session session);

    boolean delete(ID id, Session session);

    T search(ID id, Session session);

    List<T> getAll(Session session);

    String generateNextId();
}