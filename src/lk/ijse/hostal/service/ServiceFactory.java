package lk.ijse.hostal.service;

import lk.ijse.hostal.service.custom.imple.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION,LOGIN_DETAILS,CUSTOM
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOM:
                return new CustomBOImple();
            case STUDENT:
                return new StudentBOImple();
            case ROOM:
                return new RoomBOImple();
            case RESERVATION:
                return new ReservationBOImple();
            case LOGIN_DETAILS:
                return new LoginDetailsBOImple();
            default:
                return null;
        }
    }
}