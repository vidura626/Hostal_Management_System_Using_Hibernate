package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.repository.custom.ReservationRepository;
import org.hibernate.Session;

import java.util.List;

public class ReservationRepositoryImple implements ReservationRepository {
    @Override
    public Integer add(Reservation obj, Session session) {
        return null;
    }

    @Override
    public Integer update(Reservation obj, Session session) {
        return null;
    }

    @Override
    public boolean delete(Integer integer, Session session) {
        return false;
    }

    @Override
    public Reservation search(Integer integer, Session session) {
        return null;
    }

    @Override
    public List<Reservation> getAll(Session session) {
        return null;
    }
}
