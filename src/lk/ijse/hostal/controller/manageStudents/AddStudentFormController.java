package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.Routes;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.embedded.Name;
import lk.ijse.hostal.entity.Gender;
import lk.ijse.hostal.entity.Reservation;
import lk.ijse.hostal.entity.embedded.Address;
import lk.ijse.hostal.entity.embedded.Contact;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.custom.StudentBO;
import lk.ijse.hostal.util.TransferObjects;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddStudentFormController {

    @FXML
    private JFXButton btnAddressDelete;

    @FXML
    private JFXButton btnContactRemove;

    @FXML
    private JFXButton btnAddressEdit;

    @FXML
    private JFXButton btnContactEdit;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblID;

    @FXML
    private ImageView imgWebCam;

    @FXML
    private JFXComboBox<Address> cmbAddress;

    @FXML
    private JFXComboBox<Contact> cmbContact;

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
    private JFXDatePicker datePickerDOB;

    @FXML
    private JFXRadioButton rdoMale;

    @FXML
    private ToggleGroup tgleGender;

    @FXML
    private JFXRadioButton rdoFemale;

    @FXML
    private JFXRadioButton rdoOther;

    private Stage stage = new Stage();
    private List<Stage> stagesAddress = new ArrayList<>();
    private List<Stage> stagesContacts = new ArrayList<>();
    private final StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);


    public void initialize() {
        setButtonVisibility();
    }

    void setButtonVisibility() {
        if (cmbAddress.getSelectionModel().isEmpty()) {
            btnAddressDelete.setVisible(false);
            btnAddressEdit.setVisible(false);
        } else {
            btnAddressDelete.setVisible(true);
            btnAddressEdit.setVisible(true);
        }
        if (cmbContact.getSelectionModel().isEmpty()) {
            btnContactRemove.setVisible(false);
            btnContactEdit.setVisible(false);
        } else {
            btnContactRemove.setVisible(true);
            btnContactEdit.setVisible(true);
        }
    }

    Stage popUpWindow(List<Stage> stages, Routes route) throws Exception {

        if (stage.isShowing()) {
            stage.hide();
        }
        AnchorPane anchorPane = new AnchorPane();
        stage.setScene(new Scene(anchorPane));
        Navigation.navigate(route, anchorPane);
        stage.requestFocus();
        stage.showAndWait();
        return stage;

    }

    @FXML
    void btnAddAddressOnAction(ActionEvent event) throws Exception {

        /*  Pop up address window                  */
        stage = popUpWindow(stagesAddress, Routes.ADDRESS_FORM);
        if (!stage.isShowing()) return;
        /*-----------------------------------------*/

        /*  Get address */
        Address address = (Address) TransferObjects.recieveObject();
        TransferObjects.clear();
        /*--------------*/

        /*  set combobox values */
        cmbAddress.getItems().add(address);
        cmbAddress.getSelectionModel().select(address);
        /*----------------------*/

        stagesAddress.add(stage);
        cmbAddressOnAction(event);
    }

    @FXML
    void btnAddContactOnAction(ActionEvent event) throws Exception {

        /*  Pop up contact window                  */
        Stage stage = popUpWindow(stagesContacts, Routes.CONTACT_FORM);
        /*-----------------------------------------*/

        /*  Get address */
        Contact contact = (Contact) TransferObjects.recieveObject();
        TransferObjects.clear();
        /*--------------*/

        /*  set combobox values */
        cmbContact.getItems().add(contact);
        cmbContact.getSelectionModel().select(contact);
        /*----------------------*/

        stagesContacts.add(stage);
        cmbContactOnAction(event);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        /*  Name    */
        String fName = txtFName.getText();
        String mName = txtMName.getText();
        String lName = txtLName.getText();
        /*----------*/

        /*  Address */
        ObservableList<Address> address = cmbAddress.getItems();
        /*----------*/

        /*  Contacts */
        ObservableList<Contact> contacts = cmbContact.getItems();
        /*-----------*/

        /*  Email   */
        String email = txtEmail.getText();
        /*----------*/

        /*  Nic     */
        String nic = txtNic.getText();
        /*----------*/

        /*  Gender  */
        Gender gender = rdoMale.isSelected() ? Gender.MALE : rdoFemale.isSelected() ? Gender.FEMALE : rdoOther.isSelected() ? Gender.OTHER : null;
        /*----------*/

        /*  DOB     */
        LocalDate date = datePickerDOB.getValue();
        Date dob = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        /*----------*/

        /*  Create Student DTO Object   */
        StudentDTO studentDTO = new StudentDTO(
                lblID.getId(),
                nic,
                email,
                new Name(fName, mName, lName),
                address,
                contacts,
                dob,
                gender,
                Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                new ArrayList<Reservation>()
        );
        /*------------------------------*/

        boolean isSaved = false;
        try {
            isSaved = studentBO.registerStudent(studentDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();

                txtNic.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Added Failed !").show();
        }
    }

    @FXML
    void btnCaptureOnAction(ActionEvent event) {

    }

    @FXML
    void btnEditAddressOnAction(ActionEvent event) {
        int selectedIndex = cmbAddress.getSelectionModel().getSelectedIndex();
        stagesAddress.get(selectedIndex).show();
    }

    @FXML
    void btnEditContactOnAction(ActionEvent event) {
        int selectedIndex = cmbContact.getSelectionModel().getSelectedIndex();
        stagesContacts.get(selectedIndex).show();
    }

    @FXML
    void cmbAddressOnAction(ActionEvent event) {
        if (!cmbAddress.getSelectionModel().isEmpty()) {
            btnAddressEdit.setVisible(true);
            btnAddressDelete.setVisible(true);
        }
    }

    @FXML
    void cmbContactOnAction(ActionEvent event) {
        if (!cmbContact.getSelectionModel().isEmpty()) {
            btnContactEdit.setVisible(true);
            btnContactRemove.setVisible(true);
        }
    }

    @FXML
    void btnRemoveAddressOnAction(ActionEvent event) {
        int selectedIndex = cmbAddress.getSelectionModel().getSelectedIndex();
        stagesAddress.remove(selectedIndex);
        cmbAddress.getItems().remove(cmbAddress.getSelectionModel().getSelectedItem());
        setButtonVisibility();
    }

    @FXML
    void btnRemoveContactOnAction(ActionEvent event) {
        int selectedIndex = cmbContact.getSelectionModel().getSelectedIndex();
        stagesContacts.remove(selectedIndex);
        cmbContact.getItems().remove(cmbContact.getSelectionModel().getSelectedItem());
        setButtonVisibility();
    }
}
