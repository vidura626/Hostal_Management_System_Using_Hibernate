
package lk.ijse.hostal.entity;

import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.entity.embedded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    String id;
    String nic;
    String email;

    /*
    Try to add image of student
    */

    @Embedded
    Name name;
    @ElementCollection
    @CollectionTable(
            name = "addressess",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    List<Address> addresses;

    @ElementCollection
    @CollectionTable(
            name = "Contacts",
            joinColumns = @JoinColumn(name = "StudentId")
    )
    List<Contact> contact;
    Date dob;


    @Enumerated(EnumType.STRING)
    Gender gender;

    @CreationTimestamp
    Date joinedDate;
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    List<Reservation> reservations;
}
