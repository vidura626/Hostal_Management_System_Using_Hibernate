package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import org.hibernate.Session;

import java.util.List;

public class LoginDetailsRepositoryImple implements LoginDetailsRepository {
    @Override
    public boolean add(LoginDetails obj, Session session) {
        session.beginTransaction();
        try{
            int save = (int) session.save(obj);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean update(LoginDetails obj, Session session) {
        session.beginTransaction();
        try{
            session.update(obj);
            session.getTransaction().commit();
            return true;
        }catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Integer integer, Session session) {
        session.beginTransaction();
        try {
            LoginDetails load = session.get(LoginDetails.class, integer);
            session.delete(load);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public LoginDetails search(Integer integer, Session session) {
        try {
            return session.get(LoginDetails.class, integer);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<LoginDetails> getAll(Session session) {
        try {
            List<LoginDetails> from_loginDetails = session.createQuery("from LoginDetails").getResultList();
            return from_loginDetails;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
