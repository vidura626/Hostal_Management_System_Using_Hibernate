package lk.ijse.hostal.service.custom;

import lk.ijse.hostal.service.SuperBO;

public interface LoginDetailsBO extends SuperBO {
    boolean search(String txtUsername, String txtPassword);
}
