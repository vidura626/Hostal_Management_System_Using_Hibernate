package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.internal.QueryImpl;

import java.io.Serializable;
import java.util.List;

public class LoginDetailsRepositoryImple implements LoginDetailsRepository {
    @Override
    public String add(LoginDetails obj, Session session) throws Exception {
       return (String) session.save(obj);
    }

    @Override
    public void update(LoginDetails obj, Session session) throws Exception {
        session.update(obj);
    }

    @Override
    public void delete(String id, Session session) throws Exception {
        LoginDetails get = session.load(LoginDetails.class, id);
        session.delete(get);
        session.detach(get);
    }

    @Override
    public LoginDetails search(String id, Session session) {
        return session.get(LoginDetails.class, id);
    }

    @Override
    public List<LoginDetails> getAll(Session session) {
        List<LoginDetails> from_loginDetails = session.createQuery("from LoginDetails").getResultList();
        return from_loginDetails;
    }

    @Override
    public String generateNextId(Session session) {
        return null;
    }

    @Override
    public boolean check(String username, String password, Session session) {
        LoginDetails search = search(username, session);
        return search.getPassword().equals(password);
    }
}
