package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.repository.custom.ReservationRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;

public class ReservationRepositoryImple implements ReservationRepository {
    @Override
    public String add(Reservation obj, Session session) throws Exception {
        return (String) session.save(obj);
       /* session.detach(obj);
        return save;*/
    }

    @Override
    public void update(Reservation obj, Session session) throws Exception {
        session.detach(obj);
        session.update(obj);
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        Reservation get = session.load(Reservation.class, id);
        session.delete(get);
        session.detach(get);
    }

    @Override
    public Reservation search(String id, Session session) {
        Reservation reservation = session.get(Reservation.class, id);
        session.detach(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> getAll(Session session) {
        Query from_reservation = session.createQuery("FROM Reservation");
        from_reservation.setCacheable(true);
        return from_reservation.getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC").setMaxResults(1);
        query.setCacheable(true);
        List resultList = query.getResultList();
        if (resultList.size() > 0) {
            String id = (String) resultList.get(0);
            int newId = Integer.parseInt(id.replace("RES-", "")) + 1;
            return String.format("RES-%05d", newId);
        } else {
            return "RES-00001";
        }
    }

    @Override
    public Reservation searchReservation(String studentId, String roomId, Session session) {
        Query query = session.createQuery("FROM Reservation ORDER BY res_id DESC").setMaxResults(1);
        query.setCacheable(true);
        List<Reservation> resultList = query.getResultList();
        if(resultList.size()>0){
            return resultList.get(0);
        }
        return null;
    }
}
