package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.controller.TM.ReservationTM;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.RegexTypes;
import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Status;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.ReservationBO;
import lk.ijse.hostal.service.custom.RoomBO;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.Convertor;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReservationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXComboBox<StudentDTO> cmbSttd;

    @FXML
    private JFXComboBox<RoomDTO> cmbRoom;

    @FXML
    private TableView<ReservationTM> tblManageReservation;

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
    private Label lblId;

    @FXML
    private Label lblKeyMoney;

    @FXML
    private Label lblQty;

    @FXML
    private JFXDatePicker datepickerFrom;

    @FXML
    private JFXDatePicker datepickerTo;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXComboBox<Status> cmbboxCheckoutType;

    private ArrayList<JFXTextField> textFields = new ArrayList<>();

    private final StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);
    private final RoomBO roomBO = (RoomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ROOM);
    private final ReservationBO reservationBO = (ReservationBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.RESERVATION);

    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields,txtAmount);
        loadStudentsCmb();
        loadRoomsCmb();
        loadCheckoutCmb();
        loadId();
        setVisibility();
        setTable();
    }

    private void setTable() {
        setCellValueFactory();
        List<ReservationDTO> allReservations = reservationBO.getAllReservations();
        ObservableList<ReservationTM> reservations = FXCollections.observableArrayList();

        for (ReservationDTO reservationDTO : allReservations) {
            JFXButton update = new JFXButton("Update");
            JFXButton delete = new JFXButton("Delete");
            reservations.add(new ReservationTM(
                    reservationDTO.getRes_id(),
                    reservationDTO.getStudentId(),
                    reservationDTO.getRoom(),
                    reservationDTO.getFromDate(),
                    reservationDTO.getToDate(),
                    reservationDTO.getStatus(),
                    update,
                    delete
            ));
            update.setOnAction(event -> {

            });
            delete.setOnAction(event -> {
                if (tblManageReservation.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "select a row").show();
                    return;
                }
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();
                try {
                    if (buttonType.orElse(cancel) == ok) {
                        ReservationTM selectedItem = tblManageReservation.getSelectionModel().getSelectedItem();
                        boolean b = reservationBO.deleteReservation(selectedItem.getRes_id());
                        selectedItem.setRes_id(null);
                        if (b) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully !").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Not Deleted !").show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    initialize();
                }
            });
        }

        tblManageReservation.getItems().clear();
        tblManageReservation.setItems(reservations);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setVisibility() {
        txtAmount.setVisible(false);
    }

    private void loadCheckoutCmb() {
        ObservableList<Status> statuses = FXCollections.observableArrayList(Status.FULL, Status.NOT_FULL);
        cmbboxCheckoutType.setItems(statuses);
    }

    private void loadId() {
        try {
            lblId.setText(reservationBO.generateNextId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsCmb() {
        ObservableList<StudentDTO> students = FXCollections.observableArrayList();
        students.addAll(studentBO.getAllStudents());
        cmbSttd.setItems(students);
    }

    private void loadRoomsCmb() {
        ObservableList<RoomDTO> rooms = FXCollections.observableArrayList();
        rooms.addAll(roomBO.getAllRooms());
        cmbRoom.setItems(rooms);
    }

    @FXML
    void cmbRoomOnAction(ActionEvent event) {
        lblKeyMoney.setText("<KeyMoney>");
        if (cmbRoom.getSelectionModel().isEmpty()) return;
        lblKeyMoney.setText(String.valueOf(cmbRoom.getSelectionModel().getSelectedItem().getKey_money()));
        lblQty.setText(String.valueOf(cmbRoom.getSelectionModel().getSelectedItem().getQty()));
    }

    @FXML
    void cmbCheckOnAction(ActionEvent event) {
        txtAmount.setVisible(false);
        if (cmbboxCheckoutType.getSelectionModel().isEmpty()) return;
        if (cmbboxCheckoutType.getSelectionModel().getSelectedItem().equals(Status.NOT_FULL))
            txtAmount.setVisible(true);

    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

        //Validations
        if(cmbRoom.getSelectionModel().isEmpty()
                ||cmbSttd.getSelectionModel().isEmpty()
                ||cmbboxCheckoutType.getSelectionModel().isEmpty()
                ||datepickerFrom.getValue()==null
                ||datepickerTo.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"Check the form again !").show();
            return;
        }
        boolean validate = FormValidate.validate(textFields, RegexTypes.AMOUNT);
        if(!validate){
            return;
        }

        String res_id = lblId.getText();
        StudentDTO std = cmbSttd.getSelectionModel().getSelectedItem();
        RoomDTO room = cmbRoom.getSelectionModel().getSelectedItem();
        LocalDate valueFrom = datepickerTo.getValue();
        java.util.Date dateFrom = Date.from(valueFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate valueTo = datepickerTo.getValue();
        java.util.Date dateto = Date.from(valueTo.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Status status = cmbboxCheckoutType.getValue();
        String keyMoneyAmount = status.equals(Status.FULL) ? String.valueOf(room.getKey_money()) : txtAmount.getText();

        /*  Qty -   */
        room.setQty(room.getQty() - 1);

        try {
            Reservation reservation = new Reservation(
                    res_id,
                    dateFrom,
                    dateto,
                    Double.parseDouble(keyMoneyAmount),
                    room.getKey_money() - Double.parseDouble(keyMoneyAmount),
                    status,
                    Convertor.toStudent(std),
                    Convertor.toRoom(room)
            );
            std.getReservations().add(reservation);
            boolean isReserve = studentBO.updateStudent(std);
            if (isReserve) {
                new Alert(Alert.AlertType.CONFIRMATION, "Reservation is successfully ! ").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loadId();
            setVisibility();
            setTable();
            loadRoomsCmb();
            btnClearOnAction(event);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        cmbRoom.getSelectionModel().clearSelection();
        cmbSttd.getSelectionModel().clearSelection();
        cmbboxCheckoutType.getSelectionModel().clearSelection();
        datepickerFrom.resetValidation();
        datepickerTo.resetValidation();
        txtAmount.clear();
        lblKeyMoney.setText("<KeyMoney>");
        lblQty.setText("<Qty>");
    }
}
