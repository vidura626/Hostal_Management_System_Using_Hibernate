package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    boolean makeReservation(ReservationDTO reservationDTO) throws Exception;
    ReservationDTO searchReservation(String res_id) throws Exception;
    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;
    List<ReservationDTO> getAllReservations();
}
