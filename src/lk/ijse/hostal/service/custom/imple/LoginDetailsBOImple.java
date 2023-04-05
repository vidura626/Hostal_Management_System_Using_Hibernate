package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class LoginDetailsBOImple implements LoginDetailsBO {
    private Session session = FactoryConfiguration.getInstance().getSession();
    private LoginDetailsRepository loginRepo = (LoginDetailsRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.LOGIN);

    @Override
    public LoginDetailsDTO search(String username, String password) {
        return null;
    }

    @Override
    public boolean check(String username, String password) {
        return false;
    }

    @Override
    public boolean register(LoginDetailsDTO loginDetailsDTO) {
        return loginRepo.add(Convertor.toLoginDetails(loginDetailsDTO), session) == null;
    }

    @Override
    public boolean update(LoginDetailsDTO loginDetailsDTO) {
        return false;
    }

    @Override
    public List<LoginDetailsDTO> getAll() {
        return null;
    }
}
