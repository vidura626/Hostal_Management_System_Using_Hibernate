package lk.ijse.hostal.repository.custom.imple;

import lk.ijse.hostal.entity.LoginDetails;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDetailsRepositoryImple implements LoginDetailsRepository {
    @Override
    public Integer add(LoginDetails obj, Session session) throws Exception {
        return (Integer) session.save(obj);
    }

    @Override
    public void update(LoginDetails obj, Session session) throws Exception {
        session.detach(obj);
        session.saveOrUpdate(obj);
    }

    @Override
    public void delete(Integer id, Session session) throws Exception {
        LoginDetails get = session.load(LoginDetails.class, id);
        session.delete(get);
    }

    @Override
    public LoginDetails search(Integer id, Session session) {
        return session.get(LoginDetails.class, id);
    }

    @Override
    public List<LoginDetails> getAll(Session session) {
        Query from_loginDetails = session.createQuery("from LoginDetails");
        from_loginDetails.setCacheable(true);
        List<LoginDetails> resultList = from_loginDetails.getResultList();
        return resultList;
    }

    @Override
    public String generateNextId(Session session) {
        Query query = session.createQuery("SELECT id FROM LoginDetails ORDER BY id DESC").setMaxResults(1);
        query.setCacheable(true);
        List resultList = query.getResultList();
        if (resultList.size() > 0) {
            return String.valueOf((int) resultList.get(0) + 1);
        }
        return String.valueOf(1);
    }

    @Override
    public boolean check(int id, String password, Session session) {
        LoginDetails search = search(id, session);
        return search.getPassword().equals(password);
    }
}
