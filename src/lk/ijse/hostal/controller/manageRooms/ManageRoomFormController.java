package lk.ijse.hostal.controller.manageRooms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.controller.TM.MngRoomTM;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.RoomBO;

import javax.persistence.PersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ManageRoomFormController {

    public AnchorPane pane;
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableView<MngRoomTM> tblManageRoom;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtType;

    RoomBO roomBO = (RoomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ROOM);

    public void initialize() {
        setCellValueFactory();
        setAllData();
    }

    private void setAllData() {
        tblManageRoom.getItems().clear();
        ObservableList<MngRoomTM> items = tblManageRoom.getItems();
        List<RoomDTO> allRooms = roomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            JFXButton update = new JFXButton("Update");
            JFXButton delete = new JFXButton("Delete");
            items.add(new MngRoomTM(
                    roomDTO.getRoom_type_id(),
                    roomDTO.getType(),
                    roomDTO.getKey_money(),
                    roomDTO.getQty(),
                    update,
                    delete
            ));


            update.setOnAction(event -> {
            });

            delete.setOnAction(event -> {
                System.out.println("Deleted");
            });
        }
        tblManageRoom.refresh();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    @FXML
    void btnAddRoomOnAction() {
        try {
            String id = txtId.getText();
            String type = txtType.getText();
            double keyMoney = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            RoomDTO roomDTO = new RoomDTO(id, type, keyMoney, qty);

            boolean b = roomBO.addRoom(roomDTO);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room is added Successfully !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room is not added !").show();
            }
            setAllData();
            clear();
        } catch (PersistenceException | SQLIntegrityConstraintViolationException e) {
            new Alert(Alert.AlertType.ERROR, "Already added !").show();
            clear();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Check the form again !").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Room is not added !").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtQty.clear();
        txtType.clear();
        txtPrice.clear();
        txtId.clear();
    }

    @FXML
    void paneOnMouseClickOnAction(MouseEvent event) {
        if (event.getClickCount() == 2) {
            clear();
            tblManageRoom.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void tblMngRoomOoMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
            txtId.setText(selectedItem.getId());
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));
            txtType.setText(selectedItem.getType());
            txtQty.setText(String.valueOf(selectedItem.getQty()));
        }
    }

    @FXML
    void editColStart(ActionEvent event) {
        colId.setOnEditStart(event1 -> {
            System.out.println("srf");
        });
    }

}
