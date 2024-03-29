package lk.ijse.hostal.repository;

import lk.ijse.hostal.repository.custom.imple.*;
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
        STUDENT, ROOM, RESERVATION,LOGIN, CUSTOM
    }

    public SuperRepo getRepository(Repo repo) {
        switch (repo) {
            case CUSTOM:
                return new CustomRepositoryImple();
            case STUDENT:
                return new StudentRepositoryImple();
            case ROOM:
                return new RoomRepositoryImple();
            case RESERVATION:
                return new ReservationRepositoryImple();
            case LOGIN:
                return new LoginDetailsRepositoryImple();
            default:
                return null;
        }
    }
}
