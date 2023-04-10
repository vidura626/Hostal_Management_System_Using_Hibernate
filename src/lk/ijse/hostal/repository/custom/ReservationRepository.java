package lk.ijse.hostal.repository.custom;

import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.repository.CRUDUtil;
import org.hibernate.Session;

public interface ReservationRepository extends CRUDUtil<Reservation, String> {
    Reservation searchReservation(String studentId, String roomId, Session session);
}
