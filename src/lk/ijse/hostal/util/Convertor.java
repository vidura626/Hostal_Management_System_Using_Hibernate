package lk.ijse.hostal.util;

import lk.ijse.hostal.controller.TM.MngRoomTM;
import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.embedded.Name;
import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Convertor {

    public static StudentDTO fromStudent(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getNic(),
                student.getEmail(),
                new Name(
                        student.getName().getFName(),
                        student.getName().getMName(),
                        student.getName().getLName()
                ),
                student.getAddresses(),
                student.getContact(),
                student.getDob(),
                student.getGender(),
                student.getJoinedDate(),
                student.getReservations()
        );
    }

    public static Student toStudent(StudentDTO student) {
        return new Student(
                student.getId(),
                student.getNic(),
                student.getEmail(),
                new lk.ijse.hostal.entity.embedded.Name(
                        student.getName().getFName(),
                        student.getName().getMName(),
                        student.getName().getLName()
                ),
                student.getAddresses(),
                student.getContact(),
                student.getDob(),
                student.getGender(),
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                student.getReservations()
        );
    }

    public static RoomDTO fromRoom(Room room) {
        return new RoomDTO(
                room.getRoom_type_id(),
                room.getType().toString(),
                room.getKey_money(),
                room.getQty(),
                room.getReservations()
        );
    }

    public static Room toRoom(RoomDTO room) {
        return new Room(
                room.getRoom_type_id(),
                RoomDTO.RoomType.valueOf(room.getType()),
                room.getKey_money(),
                room.getQty(),
                room.getReservations()
        );
    }

    public static ReservationDTO fromReservation(Reservation reservation) {
        return new ReservationDTO(
                reservation.getRes_id(),
                reservation.getFromDate(),
                reservation.getToDate(),
                reservation.getKeyMoneyAmount(),
                reservation.getRemainingAmount(),
                reservation.getStatus(),
                reservation.getStudentId(),
                reservation.getRoom()
        );
    }

    public static Reservation toReservation(ReservationDTO reservation) {
        return new Reservation(
                reservation.getRes_id(),
                reservation.getFromDate(),
                reservation.getToDate(),
                reservation.getKeyMoneyAmount(),
                reservation.getRemainingAmount(),
                reservation.getStatus(),
                reservation.getStudentId(),
                reservation.getRoom()
        );
    }

    public static LoginDetailsDTO fromLoginDetails(LoginDetails loginDetails) {

        return null;
    }

    public static LoginDetails toLoginDetails(LoginDetailsDTO loginDetails) {

        return null;
    }
}
