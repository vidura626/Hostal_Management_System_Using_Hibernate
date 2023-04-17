package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.repository.custom.RoomRepository;
import org.hibernate.Session;

import java.util.List;

public class RoomRepositoryImple implements RoomRepository {

    @Override
    public String add(Room obj, Session session) throws Exception {
        return (String) session.save(obj);
    }

    @Override
    public void update(Room obj, Session session) throws Exception {
        session.update(obj);
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        Room get = session.load(Room.class, id);
        session.delete(get);
    }

    @Override
    public Room search(String id, Session session) {
        return session.get(Room.class, id);
    }

    @Override
    public List<Room> getAll(Session session) {
        return session.createQuery("FROM Room").getResultList();
    }

    @Override
    public String generateNextId(Session session) {
        List resultList = session.createQuery("SELECT id FROM Room ORDER BY id DESC").setMaxResults(1).getResultList();
        if (resultList.size() > 0) {
            String id = (String) resultList.get(0);
            int newId = Integer.parseInt(id.replace("RM-", "")) + 1;
            return String.format("RM-%03d", newId);
        } else {
            return "RM-001";
        }
    }
}
