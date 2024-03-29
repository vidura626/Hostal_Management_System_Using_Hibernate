package lk.ijse.hostal.controller.manageStudents;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.hostal.controller.util.FormValidate;
import lk.ijse.hostal.controller.util.Navigation;
import lk.ijse.hostal.controller.util.RegexTypes;
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

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnRegister;

    private ArrayList<JFXTextField> textFields = new ArrayList<>();
    private boolean isUpdate = false;

    private Stage stage = new Stage();
    private List<Stage> stagesAddress = new ArrayList<>();
    private List<Stage> stagesContacts = new ArrayList<>();
    private final StudentBO studentBO = (StudentBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STUDENT);


    public void initialize() {
        textFields.clear();
        Collections.addAll(textFields, txtFName, txtMName, txtLName, txtEmail, txtNic);
        setId();
        setButtonVisibility();
        update();
    }

    private void setId() {
        try {
            String id = studentBO.generateLastId();
            lblID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update() {
        StudentDTO studentDTO = (StudentDTO) TransferObjects.recieveObject();
        TransferObjects.clear();
        btnRegister.setText("REGISTER");

        if (studentDTO == null) return;

        btnRegister.setText("UPDATE");

        lblID.setText(studentDTO.getId());
        txtNic.setText(studentDTO.getNic());
        txtEmail.setText(studentDTO.getEmail());
        txtFName.setText(studentDTO.getName().getFName());
        txtMName.setText(studentDTO.getName().getMName());
        txtLName.setText(studentDTO.getName().getLName());

        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        contacts.addAll(studentDTO.getContact());

        ObservableList<Address> addresses = FXCollections.observableArrayList();
        addresses.addAll(studentDTO.getAddresses());

        cmbContact.setItems(contacts);
        cmbAddress.setItems(addresses);
        switch (studentDTO.getGender()) {
            case MALE:
                rdoMale.setSelected(true);
                break;
            case FEMALE:
                rdoFemale.setSelected(true);
                break;
            case OTHER:
                rdoOther.setSelected(true);
                break;
        }
        datePickerDOB.setValue(studentDTO.getDob().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        try {

            for (int i = 0; i < contacts.size(); i++) {
                /*  Contact */
                Stage stageContacts = new Stage();
                AnchorPane paneContact = FXMLLoader.load(getClass().getResource("/lk/ijse/hostal/view/manageStudents/ContactForm.fxml"));

                JFXTextField description = (JFXTextField) paneContact.lookup("#txtDescription");
                description.setText(contacts.get(i).getDescription());

                JFXTextField number = (JFXTextField) paneContact.lookup("#txtContactNumber");
                number.setText(contacts.get(i).getNumber());

                stageContacts.setScene(new Scene(paneContact));
                stagesContacts.add(stageContacts);

            }
            /*----------*/

            /*  Address */
            for (int i = 0; i < addresses.size(); i++) {
                AnchorPane paneAddress = FXMLLoader.load(getClass().getResource("/lk/ijse/hostal/view/manageStudents/AddressForm.fxml"));
                Stage stageAddress = new Stage();

                JFXTextField houseNo = (JFXTextField) paneAddress.lookup("#txtHouseNo");
                houseNo.setText(addresses.get(i).getHouseNo());

                JFXTextField streetNumber = (JFXTextField) paneAddress.lookup("#txtStreetName");
                streetNumber.setText(addresses.get(i).getStreetName());

                JFXTextField town = (JFXTextField) paneAddress.lookup("#txtTown");
                town.setText(addresses.get(i).getHouseNo());

                JFXTextField postalCode = (JFXTextField) paneAddress.lookup("#txtPostalCode");
                postalCode.setText(addresses.get(i).getPostalCode());

                stageAddress.setScene(new Scene(paneAddress));
                stagesAddress.add(stageAddress);
            }
            /*----------*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        isUpdate = true;
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
        /*-----------------------------------------*/

        if (TransferObjects.recieveObject() == null) return;

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

        if (TransferObjects.recieveObject() == null) return;

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
        /*  Validation  */
        boolean validate = FormValidate.validate(textFields, RegexTypes.NAME, RegexTypes.NAME, RegexTypes.NAME,
                RegexTypes.EMAIL, RegexTypes.NIC);
        if (!validate) return;
        if (datePickerDOB.getValue() == null
                || tgleGender.selectedToggleProperty().isNull().getValue()
                || cmbAddress.getSelectionModel().isEmpty()
                || cmbContact.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Fill the form correctly !").show();
            return;
        }
        /*--------------*/

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
                lblID.getText(),
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
            if (isUpdate) {
                boolean b = studentBO.updateStudent(studentDTO);
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Success !").show();
                }
            } else {
                isSaved = studentBO.registerStudent(studentDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();
                }
            }
        } catch (PersistenceException e) {
            new Alert(Alert.AlertType.WARNING, "Already added !").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Added Failed !").show();
        } finally {
            initialize();
            txtNic.getScene().getWindow().hide();
        }
    }

    private void setFocusColor() {
        txtFName.setFocusColor(Paint.valueOf("GREEN"));
        txtMName.setFocusColor(Paint.valueOf("GREEN"));
        txtLName.setFocusColor(Paint.valueOf("GREEN"));
        txtEmail.setFocusColor(Paint.valueOf("GREEN"));
        txtNic.setFocusColor(Paint.valueOf("GREEN"));
        cmbContact.setFocusColor(Paint.valueOf("GREEN"));
        cmbAddress.setFocusColor(Paint.valueOf("GREEN"));
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

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }
}
