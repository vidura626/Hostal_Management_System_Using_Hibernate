package lk.ijse.hostal.dto;

import lk.ijse.hostal.dto.embedded.Name;
import lk.ijse.hostal.entity.Gender;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    String id;
    String nic;
    String email;
    Name name;
    List<Address> addresses;
    List<Contact> contact;
    Date dob;
    Gender gender;
    Date joinedDate;
    List<Reservation> reservations;
}
