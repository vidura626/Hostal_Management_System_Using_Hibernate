package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Gender;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.entity.embedded.Name;

import java.util.Date;
import java.util.List;

public class StudentDTO {
    String id;
    String nic;
    String email;
    Name name;
    List<Address> addresses;
    List<Contact> contact;
    Date dob;
    Gender gender;
}
