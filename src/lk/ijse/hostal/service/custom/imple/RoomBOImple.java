package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.RoomRepository;
import lk.ijse.hostal.service.custom.RoomBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class RoomBOImple implements RoomBO {
    private Session session;
    private RoomRepository repo = (RoomRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.ROOM);

    @Override
    public boolean addRoom(RoomDTO roomDto) throws Exception {
        openSession();
        repo.add(Convertor.toRoom(roomDto), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDto) throws Exception {
        openSession();
        repo.update(Convertor.toRoom(roomDto), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean deleteRoom(String roomId) throws Exception {
        openSession();
        repo.delete(roomId,session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public RoomDTO searchRoom(String id) {
        openSession();
        RoomDTO roomDTO = Convertor.fromRoom(repo.search(id, session));
        closeAndCommitSession();
        return roomDTO;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        openSession();
        List<RoomDTO> collect = repo.getAll(session).stream().map(Convertor::fromRoom).collect(Collectors.toList());
        closeAndCommitSession();
        return collect;
    }

    @Override
    public String generateNextId() throws Exception {
        openSession();
        String id = repo.generateNextId(session);
        closeAndCommitSession();
        return id;
    }

    @Override
    public void openSession() {
        session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
    }

    @Override
    public void closeAndCommitSession() {
        session.getTransaction().commit();
        session.close();
    }
}
