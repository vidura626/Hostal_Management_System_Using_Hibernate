package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.repository.custom.RoomRepository;
import org.hibernate.Session;

import java.util.List;

public class RoomRepositoryImple implements RoomRepository {

    @Override
    public boolean add(Room obj, Session session) {
        session.beginTransaction();
        try{
            String save = (String) session.save(obj);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean update(Room obj, Session session) {
        session.beginTransaction();
        try {
            session.update(obj);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(String id, Session session) {
        session.beginTransaction();
        try {
            Reservation load = session.get(Reservation.class, id);
            session.update(load);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Room search(String id, Session session) {
        try {
            return session.get(Room.class, id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Room> getAll(Session session) {
        try {
            List<Room> from_reservation = session.createQuery("FROM Room").getResultList();
            return from_reservation;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
