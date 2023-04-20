package lk.ijse.hostal.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Reservation {
    @Id
    private String res_id;
    private Date fromDate;
    private Date toDate;
    private double keyMoneyAmount;
    private double remainingAmount;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Student studentId;

    @ManyToOne
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    private Room room;
}
