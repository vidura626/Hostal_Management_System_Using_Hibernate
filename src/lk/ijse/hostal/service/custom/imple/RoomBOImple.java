package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.RoomRepository;
import lk.ijse.hostal.service.custom.RoomBO;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomBOImple implements RoomBO {
    private Session session;
    private RoomRepository repo = (RoomRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.ROOM);

    @Override
    public boolean addRoom(RoomDTO roomDto) {
        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDto) {
        return false;
    }

    @Override
    public RoomDTO searchRoom(String id) {
        return null;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return null;
    }

    @Override
    public int getAvalability(String roomTypeId) {
        return 0;
    }

    @Override
    public boolean checkAvalability(String roomTypeId) {
        return false;
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
