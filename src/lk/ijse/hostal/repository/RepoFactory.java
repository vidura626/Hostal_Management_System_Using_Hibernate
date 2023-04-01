package lk.ijse.hostal.repository;

import lk.ijse.hostal.repository.custom.imple.ReservationRepositoryImple;
import lk.ijse.hostal.repository.custom.imple.StudentRepositoryImple;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;

public class RepoFactory {
    private static RepoFactory repoFactory;
    private Session session = FactoryConfiguration.getInstance().getSession();

    private RepoFactory() {

    }

    public static RepoFactory getInstance() {
        return repoFactory == null ? repoFactory = new RepoFactory() : repoFactory;
    }

    public enum Repo {
        STUDENT, ROOM, RESERVATION
    }

    private SuperRepo getRepository(Repo repo) {
        switch (repo) {
            case STUDENT:
                return new StudentRepositoryImple();
            case ROOM:
                return new StudentRepositoryImple();
            case RESERVATION:
                return new ReservationRepositoryImple();
            default:
                return null;
        }
    }
}
