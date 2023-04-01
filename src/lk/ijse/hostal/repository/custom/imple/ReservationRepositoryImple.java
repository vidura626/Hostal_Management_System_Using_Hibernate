package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.repository.custom.ReservationRepository;
import org.hibernate.Session;

import java.util.List;

public class ReservationRepositoryImple implements ReservationRepository {
    @Override
    public String add(Reservation obj, Session session) {
        session.beginTransaction();
        try {
            String save = (String) session.save(obj);
            session.getTransaction().commit();
            return save;
        }catch (Exception e){
            session.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean update(Reservation obj, Session session) {
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
    public Reservation search(String id, Session session) {
        try {
            return session.get(Reservation.class, id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Reservation> getAll(Session session) {
        try {
            List<Reservation> from_reservation = session.createQuery("FROM Reservation").getResultList();
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
