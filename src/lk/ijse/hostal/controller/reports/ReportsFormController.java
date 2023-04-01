package lk.ijse.hostal.controller.reports;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportsFormController {

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colResId;

    @FXML
    private TableColumn<?, ?> colRoom;

    @FXML
    private TableColumn<?, ?> colStd;

    @FXML
    private TableColumn<?, ?> colStdDate;

    @FXML
    private TableColumn<?, ?> colStdId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private Tab tabPaneRooms;

    @FXML
    private Tab tabPaneStudents;

    @FXML
    private Tab tabpaneReservation;

    @FXML
    private TableView<?> tblReservations;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TableView<?> tblTytpeId;

}
