package lk.ijse.hostal.controller.manageStudents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostal.controller.TM.ViewAddressTM;
import lk.ijse.hostal.controller.TM.ViewContactTM;
import lk.ijse.hostal.controller.TM.ViewReservationForStdTM;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.StudentBO;

import java.util.List;

public class ViewReservtionsFormController {

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
    private TableView<ViewAddressTM> tblManageReservation11;

    @FXML
    private TableColumn<?, ?> colAddress;

    private StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);

    public void initialize(){
        setCellValueFactory();
        loadData();
    }

    private void loadData() {
        List<StudentDTO> allStudents = studentBO.getAllStudents();

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
