package lk.ijse.hostal.repository.custom;

import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.repository.CRUDUtil;
import org.hibernate.Session;

public interface LoginDetailsRepository extends CRUDUtil<LoginDetails, Integer> {
    public boolean check(int id, String password, Session session) throws Exception;
}
