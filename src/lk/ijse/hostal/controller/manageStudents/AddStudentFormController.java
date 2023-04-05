package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.TransferObjects;

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

    private Stage stage = new Stage();

    private final StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);

    @FXML
     void btnAddAddressOnAction(ActionEvent event) throws Exception {

        /*  Pop up address window   */
        if (stage.isShowing()) {
            stage.close();
        } else {
            AnchorPane anchorPane = new AnchorPane();
            stage.setScene(new Scene(anchorPane));
            Navigation.navigate(Routes.ADDRESS_FORM, anchorPane);
            stage.showAndWait();
        }
        /*-----------------------------------------*/

        /*  Get address*/
        String address = (String) TransferObjects.recieveObject();
        System.out.println(address);
        TransferObjects.clear();
        /*--------------*/

    }

    @FXML
    void btnAddContactOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        /*  Name    */
        String fName = txtFName.getText();
        String mName = txtMName.getText();
        String lName = txtLName.getText();

        /*  Address */

    }

    @FXML
    void btnCaptureOnAction(ActionEvent event) {

    }

}
