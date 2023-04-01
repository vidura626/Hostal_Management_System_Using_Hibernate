package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.ReservationRepository;
import lk.ijse.hostal.service.custom.ReservationBO;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class ReservationBOImple implements ReservationBO {
    private Session session = FactoryConfiguration.getInstance().getSession();
    private ReservationRepository repo = (ReservationRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.RESERVATION);

    @Override
    public boolean makeReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean checkAvalability(String roomTypeId) {
        return false;
    }

    @Override
    public int getAvalability(String roomTypeId) {
        return 0;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return null;
    }
}
