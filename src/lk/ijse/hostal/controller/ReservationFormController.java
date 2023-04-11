package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReservationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXComboBox<?> cmbSttd;

    @FXML
    private JFXComboBox<?> cmbRoom;

    @FXML
    private TableView<?> tblManageReservation;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colFrom;

    @FXML
    private TableColumn<?, ?> colTo;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private Label txtID;

    @FXML
    private Label txtKeyMoney;

    @FXML
    private JFXDatePicker datepickerFrom;

    @FXML
    private JFXDatePicker datepickerTo;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXComboBox<?> cmbboxCheckoutType;

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }
}
