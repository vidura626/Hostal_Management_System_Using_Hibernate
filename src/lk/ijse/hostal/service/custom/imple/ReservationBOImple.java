package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.ReservationRepository;
import lk.ijse.hostal.service.custom.ReservationBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationBOImple implements ReservationBO {
    private Session session;
    private ReservationRepository repo = (ReservationRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.RESERVATION);

    @Override
    public boolean makeReservation(ReservationDTO reservationDTO) throws Exception {
        openSession();
        repo.add(Convertor.toReservation(reservationDTO), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public ReservationDTO searchReservation(String res_id) throws Exception {
        openSession();
        return Convertor.fromReservation(repo.search(res_id, session));
    }

    @Override
    public ReservationDTO searchReservation(String studentId, String roomId) {
        openSession();
        return null;
    }

    @Override
    public ReservationDTO searchReservation(StudentDTO student, RoomDTO room) {
        return null;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        openSession();
        repo.update(Convertor.toReservation(reservationDTO), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        openSession();
        return repo.getAll(session).stream().map(Convertor::fromReservation).collect(Collectors.toList());
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
