package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReservationFormController {

    @FXML
    private JFXComboBox<?> cmbRoom;

    @FXML
    private JFXComboBox<?> cmbSttd;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colRoom;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStd;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<?> tblManageReservation;

    @FXML
    private Label txtID;

    @FXML
    private TextField txtStatus;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

}
