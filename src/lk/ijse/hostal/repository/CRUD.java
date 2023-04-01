package lk.ijse.hostal.repository;

import org.hibernate.Session;

import java.util.List;

public interface CRUD<T,ID> extends SuperRepo{
    ID add(T obj, Session session);
    ID update(T obj, Session session);
    boolean delete(ID id, Session session);
    T search(ID id, Session session);
    List<T> getAll(Session session);
}
