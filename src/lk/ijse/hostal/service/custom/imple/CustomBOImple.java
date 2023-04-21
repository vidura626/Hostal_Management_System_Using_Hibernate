package lk.ijse.hostal.service.custom.imple;

import lk.ijse.hostal.dto.CustomDTO;
import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.entity.Custom;
import lk.ijse.hostal.repository.RepoFactory;
import lk.ijse.hostal.repository.custom.CustomRepository;
import lk.ijse.hostal.repository.custom.LoginDetailsRepository;
import lk.ijse.hostal.service.custom.CustomBO;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class CustomBOImple implements CustomBO {
    private Session session;
    private CustomRepository customRepo = (CustomRepository) RepoFactory.getInstance().getRepository(RepoFactory.Repo.CUSTOM);

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

    @Override
    public List<CustomDTO> getList() {
        openSession();
        List<Custom> notFullPaidList = customRepo.getNotFullPaidList(session);
        List<CustomDTO> collect = notFullPaidList.stream().map(Convertor::fromCustom).collect(Collectors.toList());
        closeAndCommitSession();
        return collect;
    }
}
