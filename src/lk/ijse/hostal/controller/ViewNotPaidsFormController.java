package lk.ijse.hostal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.dto.CustomDTO;
import lk.ijse.hostal.entity.Custom;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.CustomBO;

import java.util.List;

public class ViewNotPaidsFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<CustomDTO> tblManageReservation;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colRoom;

    @FXML
    private TableColumn<?, ?> colType;

    private final CustomBO customBO = (CustomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.CUSTOM);

    public void initialize() {
        setCellValueFactory();
        loadData();
    }

    private void loadData() {
        ObservableList<CustomDTO> objects = FXCollections.observableArrayList();
        List<CustomDTO> list = customBO.getList();
        objects.addAll(list);
        tblManageReservation.setItems(objects);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    }

}
