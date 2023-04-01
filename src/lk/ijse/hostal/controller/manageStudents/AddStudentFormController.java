package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class AddStudentFormController {

    @FXML
    private JFXComboBox<?> cmbAddress;

    @FXML
    private JFXComboBox<?> cmbContact;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup gender;

    @FXML
    private ImageView imgWebCam;

    @FXML
    private JFXRadioButton rdoFemale;

    @FXML
    private JFXRadioButton rdoMale;

    @FXML
    private JFXRadioButton rdoOther;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private Label txtID;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtMName;

    @FXML
    private TextField txtNic;

    @FXML
    void btnAddAddressOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddContactOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnCaptureOnAction(ActionEvent event) {

    }

}
