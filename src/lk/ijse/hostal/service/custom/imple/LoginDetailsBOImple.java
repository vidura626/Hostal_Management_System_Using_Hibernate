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
    public LoginDetailsDTO search(int id) {
        openSession();
        LoginDetailsDTO loginDetailsDTO = Convertor.fromLoginDetails(loginRepo.search(id, session));
        closeAndCommitSession();
        return loginDetailsDTO;
    }

    @Override
    public boolean check(int id, String password) throws Exception {
        openSession();
        boolean check = loginRepo.check(id, password, session);
        closeAndCommitSession();
        return check;
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
        openSession();
        loginRepo.update(Convertor.toLoginDetails(loginDetailsDTO), session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public boolean delete(int id) throws Exception {
        openSession();
        loginRepo.delete(id, session);
        closeAndCommitSession();
        return true;
    }

    @Override
    public int getLastId() throws Exception {
        openSession();
        int i = Integer.parseInt(loginRepo.generateNextId(session));
        closeAndCommitSession();
        return i;
    }

    @Override
    public List<LoginDetailsDTO> getAll() {
        openSession();
        List<LoginDetailsDTO> collect = loginRepo.getAll(session).stream().map(Convertor::fromLoginDetails).collect(Collectors.toList());
        closeAndCommitSession();
        return collect;
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
