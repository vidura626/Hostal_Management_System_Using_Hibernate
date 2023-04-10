package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class LoginDetailsBOImple implements LoginDetailsBO {
    private Session session;
    private LoginDetailsRepository loginRepo = (LoginDetailsRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.LOGIN);

    @Override
    public LoginDetailsDTO search(String username) {
        openSession();
        return Convertor.fromLoginDetails(loginRepo.search(username, session));
    }

    @Override
    public boolean check(String username, String password) {
        return false;
    }

    @Override
    public boolean register(LoginDetailsDTO loginDetailsDTO) throws Exception {
        openSession();
        loginRepo.add(Convertor.toLoginDetails(loginDetailsDTO), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean update(LoginDetailsDTO loginDetailsDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        loginRepo.update(Convertor.toLoginDetails(loginDetailsDTO), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean delete(String username) throws Exception {
        Transaction transaction = session.beginTransaction();
        loginRepo.delete(username, session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public List<LoginDetailsDTO> getAll() {
        openSession();
        return loginRepo.getAll(session).stream().map(Convertor::fromLoginDetails).collect(Collectors.toList());
    }

    @Override
    public void openSession() {
        session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
    }
    @Override
    public void closeAndCommitSession() {
        session.getTransaction().commit();
        session.close();
    }
}
