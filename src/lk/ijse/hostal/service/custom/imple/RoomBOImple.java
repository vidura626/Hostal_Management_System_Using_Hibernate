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
    public RoomDTO searchRoom(String id) {
        openSession();
        return Convertor.fromRoom(repo.search(id, session));
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        openSession();
        return repo.getAll(session).stream().map(Convertor::fromRoom).collect(Collectors.toList());
    }

    @Override
    public int getAvalability(String roomId) {
        openSession();
        return repo.search(roomId, session).getQty();
    }

    @Override
    public boolean checkAvalability(String roomId) {
        openSession();
        return repo.search(roomId, session).getQty() > 0;
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
