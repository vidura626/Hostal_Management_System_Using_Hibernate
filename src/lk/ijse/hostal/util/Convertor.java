package lk.ijse.hostal.util;

import lk.ijse.hostal.controller.TM.MngRoomTM;
import lk.ijse.hostal.dto.*;
import lk.ijse.hostal.dto.embedded.Name;
import lk.ijse.hostal.entity.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Convertor {

    public static CustomDTO fromCustom(Custom custom) {
        return new CustomDTO(
                custom.getReservationId(),
                custom.getStudentName(),
                custom.getRemainingAmount(),
                custom.getRoomId(),
                custom.getRoomType()
        );
    }

    public static Custom toCustom(CustomDTO custom) {
        return new Custom(
                custom.getReservationId(),
                custom.getStudentName(),
                custom.getRemainingAmount(),
                custom.getRoomId(),
                custom.getRoomType()
        );
    }

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
        return new LoginDetailsDTO(
                loginDetails.getId(),
                loginDetails.getUsername(),
                loginDetails.getName(),
                loginDetails.getEmail(),
                loginDetails.getPassword()
        );
    }

    public static LoginDetails toLoginDetails(LoginDetailsDTO loginDetails) {
        return new LoginDetails(
                loginDetails.getId(),
                loginDetails.getUsername(),
                loginDetails.getName(),
                loginDetails.getEmail(),
                loginDetails.getPassword()
        );
    }
}
