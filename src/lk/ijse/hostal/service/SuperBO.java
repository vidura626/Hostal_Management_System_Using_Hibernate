package lk.ijse.hostal.service;

import org.hibernate.Transaction;

public interface SuperBO {
    void openSession();
    void closeAndCommitSession();
}
