package lk.ijse.hostal.service;

import lk.ijse.hostal.service.custom.imple.ReservationBOImple;
import lk.ijse.hostal.service.custom.imple.RoomBOImple;
import lk.ijse.hostal.service.custom.imple.StudentBOImple;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImple();
            case ROOM:
                return new RoomBOImple();
            case RESERVATION:
                return new ReservationBOImple();
            default:
                return null;
        }
    }
}