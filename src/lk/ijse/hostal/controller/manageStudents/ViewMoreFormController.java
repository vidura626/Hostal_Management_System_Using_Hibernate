package lk.ijse.hostal.controller.manageStudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostal.controller.TM.ViewAddressTM;
import lk.ijse.hostal.controller.TM.ViewContactTM;
import lk.ijse.hostal.controller.TM.ViewReservationForStdTM;
import lk.ijse.hostal.dto.ReservationDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.TransferObjects;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ViewMoreFormController {

    @FXML
    private TableView<ViewReservationForStdTM> tblManageReservation;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colFrom;

    @FXML
    private TableColumn<?, ?> colTo;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private TableView<ViewContactTM> tblContacts;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableView<ViewAddressTM> tblAddresses;

    @FXML
    private TableColumn<?, ?> colAddress;

    private StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);

    public void initialize() {
        setCellValueFactory();
        loadData();
    }

    private void loadData() {
        String id = (String) TransferObjects.recieveObject();
        TransferObjects.clear();

        StudentDTO studentDTO = studentBO.searchStudent(id);

        /*  Student Id & Student Name   */
        lblId.setText(studentDTO.getId());
        lblName.setText(studentDTO.getName().getFName());
        /*------------------------------*/

        /*  Contact table   */
        ObservableList<ViewContactTM> contacts = FXCollections.observableArrayList();
        for (Contact c : studentDTO.getContact()) {
            contacts.add(new ViewContactTM(c.getDescription(),c.getNumber()));
        }
        tblContacts.setItems(contacts);
        /*------------------*/

        /*  Addresses   */
        ObservableList<ViewAddressTM> addresses = FXCollections.observableArrayList();
        for (Address c : studentDTO.getAddresses()) {
            addresses.add(new ViewAddressTM(c.getHouseNo().concat(" ").concat(c.getStreetName().concat(" ").concat(c.getTown().concat(" ").concat(c.getPostalCode())))));
        }
        tblAddresses.setItems(addresses);
        /*---------------*/

        /*  Reservations    */
        ObservableList<ViewReservationForStdTM> reservations = FXCollections.observableArrayList();
        for (Reservation c : studentDTO.getReservations()) {
            reservations.add(new ViewReservationForStdTM(
                    c.getRes_id(),
                    c.getRoom().getRoom_type_id(),
                    c.getFromDate(),
                    c.getToDate(),
                    c.getStatus().toString()
            ));
        }
        tblManageReservation.setItems(reservations);
        /*---------------*/
    }

    private void setCellValueFactory() {
        /*  Contact table   */
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        /*------------------*/

        /*  Address table   */
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        /*------------------*/

        /*  Reservation table   */
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        /*----------------------*/
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {

    }

}
