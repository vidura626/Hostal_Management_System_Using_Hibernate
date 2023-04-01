package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.service.SuperBO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    boolean makeReservation(ReservationDTO reservationDTO);
    boolean updateReservation(ReservationDTO reservationDTO);
    boolean checkAvalability(String roomTypeId);
    int getAvalability(String roomTypeId);
    List<ReservationDTO> getAllReservations();
}
