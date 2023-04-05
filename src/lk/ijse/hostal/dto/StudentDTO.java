package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Gender;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.entity.embedded.Name;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDTO {
    private String id;
    private String nic;
    private String email;
    private Name name;
    private List<Address> addresses = new ArrayList<>();
    private List<Contact> contact = new ArrayList<>();
    private Date dob;
    private Gender gender;
}
