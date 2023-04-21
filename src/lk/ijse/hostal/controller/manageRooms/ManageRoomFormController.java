package lk.ijse.hostal.controller.manageRooms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.StringConverter;
import lk.ijse.hostal.controller.TM.MngRoomTM;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.RegexTypes;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.RoomBO;

import javax.persistence.PersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ManageRoomFormController {

    public AnchorPane pane;
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<Object, String> colPrice;

    @FXML
    private TableColumn<Object, String> colQty;

    @FXML
    private TableColumn<Object, String> colType;

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
    private JFXComboBox<RoomDTO.RoomType> cmbType;

    private ArrayList<JFXTextField> textFields = new ArrayList<>();
    RoomBO roomBO = (RoomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ROOM);

    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields, txtId, txtPrice, txtQty);
        setCellValueFactory();
        setAllData();
        setComboBox();
        setDefaultFocusColours();

        tblManageRoom.setEditable(true);
        colType.setCellFactory(ComboBoxTableCell.forTableColumn(
                RoomDTO.RoomType.AC.toString(),
                RoomDTO.RoomType.AC_FOOD.toString(),
                RoomDTO.RoomType.NON_AC.toString(),
                RoomDTO.RoomType.NON_AC_FOOD.toString()
        ));
        colQty.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void setDefaultFocusColours() {
        cmbType.setFocusColor(null);
    }

    private void setComboBox() {
        cmbType.getItems().addAll(
                RoomDTO.RoomType.AC,
                RoomDTO.RoomType.AC_FOOD,
                RoomDTO.RoomType.NON_AC,
                RoomDTO.RoomType.NON_AC_FOOD
        );
    }

    private void setAllData() {
        tblManageRoom.getItems().clear();
        ObservableList<MngRoomTM> items = tblManageRoom.getItems();
        List<RoomDTO> allRooms = roomBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {

            /*  Buttons to TableView    */
            JFXButton update = new JFXButton("Update");
            JFXButton delete = new JFXButton("Delete");
            /*--------------------------*/

            items.add(new MngRoomTM(
                    roomDTO.getRoom_type_id(),
                    roomDTO.getType(),
                    String.valueOf(roomDTO.getKey_money()),
                    String.valueOf(roomDTO.getQty()),
                    update,
                    delete
            ));


            update.setOnAction(event -> {
                if (tblManageRoom.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Select a row").show();
                    return;
                }

                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();
                MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
                RoomDTO original = roomBO.searchRoom(selectedItem.getId());
                int selectedIndex = tblManageRoom.getSelectionModel().getSelectedIndex();

                original.setQty(22);
                if (buttonType.orElse(cancel) == ok) {
                    if (selectedItem.getType().equals(original.getType())
                            && Integer.parseInt(selectedItem.getQty()) == original.getQty()
                            && Double.parseDouble(selectedItem.getPrice()) == original.getKey_money()) {
                        new Alert(Alert.AlertType.INFORMATION,"No changes").show();
                        return;
                    }
                    try {
                        boolean b = roomBO.updateRoom(new RoomDTO(
                                selectedItem.getId(),
                                selectedItem.getType(),
                                Double.parseDouble(selectedItem.getPrice()),
                                Integer.parseInt(selectedItem.getQty()),
                                original.getReservations()
                        ));
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Not Updated !").show();
                    } finally {
                        setAllData();
                    }
                } else {
                    selectedItem.setPrice(String.valueOf(original.getKey_money()));
                    selectedItem.setQty(String.valueOf(original.getQty()));
                    selectedItem.setType(original.getType());
                    tblManageRoom.getItems().set(selectedIndex, selectedItem);
                }
            });

            delete.setDisable(false);
            if (roomDTO.getReservations().size() > 0) delete.setDisable(true);
            delete.setOnAction(event -> {
                if (tblManageRoom.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Select a row").show();
                    return;
                }
                ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();

                if (buttonType.orElse(cancel) == ok) {
                    String id = tblManageRoom.getSelectionModel().getSelectedItem().getId();
                    try {
                        boolean b = roomBO.deleteRoom(id);
                        if (b) new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully !").show();
                        setAllData();
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Not Deleted !").show();
                    }
                }
            });
        }
        tblManageRoom.refresh();
    }

    /*  Cell Edit   */
    @FXML
    void editType(TableColumn.CellEditEvent event) {
        MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
        selectedItem.setType(event.getNewValue().toString());
    }


    @FXML
    void editQty(TableColumn.CellEditEvent event) {
        MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
        boolean check = FormValidate.check(RegexTypes.QTY, event.getNewValue().toString());
        if (check) selectedItem.setQty(event.getNewValue().toString());

    }

    @FXML
    void editPrice(TableColumn.CellEditEvent event) {
        MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
        boolean check = FormValidate.check(RegexTypes.AMOUNT, event.getNewValue().toString());
        if (check) selectedItem.setPrice(event.getNewValue().toString());
    }
    /*--------------*/


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
        //Validation
        boolean validate = FormValidate.validate(textFields, RegexTypes.ROOM_ID, RegexTypes.AMOUNT, RegexTypes.QTY);
        if (!validate) {
            return;
        }
        if (cmbType.getSelectionModel().isEmpty()) {
            cmbType.setFocusColor(Paint.valueOf("RED"));
            cmbType.requestFocus();
            return;
        }

        try {
            String roomId = txtId.getText();
            RoomDTO.RoomType type = cmbType.getValue();
            double keyMoney = Double.parseDouble(txtPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());

            RoomDTO roomDTO = new RoomDTO(roomId, type.toString(), keyMoney, qty);

            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();

            if (buttonType.orElse(cancel) == ok) {
                boolean b = roomBO.addRoom(roomDTO);
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Room is added Successfully !").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Room is not added !").show();
                }
            }
        } catch (PersistenceException | SQLIntegrityConstraintViolationException e) {
            new Alert(Alert.AlertType.ERROR, "Already added !").show();
            clear();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Check the form again !").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Room is not added !").show();
        } finally {
            setAllData();
            clear();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    private void clear() {
        txtQty.clear();
        cmbType.getSelectionModel().clearSelection();
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

    /*@FXML
    void tblMngRoomOoMouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            MngRoomTM selectedItem = tblManageRoom.getSelectionModel().getSelectedItem();
            txtId.setText(selectedItem.getId());
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));
            cmbType.getSelectionModel().select(RoomDTO.RoomType.valueOf(selectedItem.getType()));
            txtQty.setText(String.valueOf(selectedItem.getQty()));
        }
    }*/
}
