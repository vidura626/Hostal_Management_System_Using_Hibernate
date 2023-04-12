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
    }

    @Override
    public void update(Reservation obj, Session session) throws Exception {
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
        return session.get(Reservation.class, id);
    }

    @Override
    public List<Reservation> getAll(Session session) {
        return session.createQuery("FROM Reservation").getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        List resultList = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC").setMaxResults(1).getResultList();
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
        List<Reservation> resultList = session.createQuery("FROM Reservation ORDER BY res_id DESC").setMaxResults(1).getResultList();
        if(resultList.size()>0){
            return resultList.get(0);
        }
        return null;
    }
}
