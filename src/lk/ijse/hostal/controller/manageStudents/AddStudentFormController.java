package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AddStudentFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label txtID;

    @FXML
    private ImageView imgWebCam;

    @FXML
    private JFXComboBox<?> cmbAddress;

    @FXML
    private JFXComboBox<?> cmbContact;

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtMName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXRadioButton rdoMale;

    @FXML
    private ToggleGroup tgleGender;

    @FXML
    private JFXRadioButton rdoFemale;

    @FXML
    private JFXRadioButton rdoOther;

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
