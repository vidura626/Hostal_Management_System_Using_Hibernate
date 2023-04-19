package lk.ijse.hostal.controller.users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.TM.MngUserTM;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.RegexTypes;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.dto.LoginDetailsDTO;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.LoginDetailsBO;
import lk.ijse.hostal.util.TransferObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private TableView<MngUserTM> tblManageUser;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword1;

    @FXML
    private JFXPasswordField txtPassword2;

    @FXML
    private JFXButton btnAddOrUpdate;

    private ArrayList<JFXTextField> textFields = new ArrayList<>();

    @FXML
    private Label lblUserId;

    private Stage stage = new Stage();

    private final LoginDetailsBO loginDetailsBO = (LoginDetailsBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.LOGIN_DETAILS);

    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields, txtName, txtEmail, txtUsername);
        btnAddOrUpdate.setText("ADD");
        setCellValueFactory();
        loadData();
        setLastId();
    }

    private void setLastId() {
        try {
            lblUserId.setText(String.valueOf(loginDetailsBO.getLastId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        tblManageUser.getItems().clear();
        List<LoginDetailsDTO> all = loginDetailsBO.getAll();
        ObservableList<MngUserTM> items = tblManageUser.getItems();
        for (LoginDetailsDTO detailsDTO : all) {
            JFXButton update = new JFXButton("Update");
            JFXButton delete = new JFXButton("Delete");
            items.add(new MngUserTM(
                    detailsDTO.getId(),
                    detailsDTO.getUsername(),
                    detailsDTO.getName(),
                    detailsDTO.getEmail(),
                    detailsDTO.getPassword(),
                    update,
                    delete
            ));

            update.setOnAction(event -> {
                if (tblManageUser.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please select a row !").show();
                    return;
                }
                try {
                    TransferObjects.sendObject(tblManageUser.getSelectionModel().getSelectedItem().getId());
                    showingStage(Routes.USER_CONFIRM, stage);
                    Boolean aBoolean = (Boolean) TransferObjects.recieveObject();
                    TransferObjects.clear();
                    if (aBoolean.equals(Boolean.FALSE)) {
                        btnClearOnAction(event);
                        return;
                    }
                    stage.close();
                    btnAddOrUpdate.setText("UPDATE");
                    lblUserId.setText(String.format("%04d", tblManageUser.getSelectionModel().getSelectedItem().getId()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Set fields to data
                MngUserTM selectedItem = tblManageUser.getSelectionModel().getSelectedItem();
                txtName.setText(selectedItem.getName());
                txtEmail.setText(selectedItem.getEmail());
                txtUsername.setText(selectedItem.getUsername());
                txtPassword1.setText(selectedItem.getPassword());
                txtPassword2.setText(selectedItem.getPassword());
            });

            //Delete User
            delete.setOnAction(event -> {
                if (tblManageUser.getSelectionModel().isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "Please select a row !").show();
                    return;
                }
                MngUserTM selectedItem = tblManageUser.getSelectionModel().getSelectedItem();
                try {
                    ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();
                    if (buttonType.orElse(cancel) == ok) {
                        boolean isDelete = loginDetailsBO.delete(selectedItem.getId());
                        if (isDelete) {
                            new Alert(Alert.AlertType.CONFIRMATION, "User Deleted !").show();
                            tblManageUser.getItems().remove(selectedItem);
                            tblManageUser.refresh();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    initialize();
                }
            });
        }
    }

    private void setCellValueFactory() {
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    @FXML
    void btnAddUserOnAction(ActionEvent event) {
        /*  Validation  */
        boolean validate = FormValidate.validate(textFields, RegexTypes.NAME, RegexTypes.EMAIL, RegexTypes.USERNAME);
        if (!validate) return;
        /*--------------*/

        txtPassword1.setFocusColor(null);
        txtPassword2.setFocusColor(null);

        int id = Integer.parseInt(lblUserId.getText());
        String username = txtUsername.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String pass_01 = txtPassword1.getText();
        String pass_02 = txtPassword2.getText();

        if (!pass_01.equals(pass_02)||pass_01.equals("")||pass_02.equals("")) {
            new Alert(Alert.AlertType.WARNING, "Check the password fields again !").show();
            txtPassword1.setFocusColor(Paint.valueOf("red"));
            txtPassword2.setFocusColor(Paint.valueOf("red"));
            txtPassword1.requestFocus();
            txtPassword2.requestFocus();
            return;
        }
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO(id, username, name, email, pass_01);
        try {
            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure?", ok, cancel).showAndWait();

            if (buttonType.orElse(cancel) == ok) {
                if (btnAddOrUpdate.getText().equals("UPDATE")) {
                    loginDetailsBO.update(loginDetailsDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully !").show();
                } else {
                    loginDetailsBO.register(loginDetailsDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "User added successfully !").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Already added !").show();
        } finally {
            initialize();
            btnClearOnAction(event);
            btnAddOrUpdate.setText("ADD");
        }


    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtEmail.clear();
        txtName.clear();
        txtPassword1.clear();
        txtPassword2.clear();
        txtUsername.clear();
    }

    @FXML
    void paneOnMouseClickOnAction(MouseEvent event) {

    }

    void showingStage(Routes route, Stage stage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        stage.setScene(new Scene(anchorPane));
        Navigation.navigate(route, anchorPane);
        if (stage.isShowing()) {
            stage.hide();
        } else {
            stage.showAndWait();
        }
    }
}
