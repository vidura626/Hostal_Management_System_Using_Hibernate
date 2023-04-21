package lk.ijse.hostal.repository.custom;

import lk.ijse.hostal.entity.Custom;
import lk.ijse.hostal.repository.SuperRepo;
import org.hibernate.Session;

import java.util.List;

public interface CustomRepository extends SuperRepo {
    public List<Custom> getNotFullPaidList(Session session);
}
