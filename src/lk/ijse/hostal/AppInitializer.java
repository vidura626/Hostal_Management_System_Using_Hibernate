package lk.ijse.hostal;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.hostal.dto.CustomDTO;
import lk.ijse.hostal.entity.Custom;
import lk.ijse.hostal.repository.custom.CustomRepository;
import lk.ijse.hostal.repository.custom.imple.CustomRepositoryImple;
import lk.ijse.hostal.service.ServiceFactory;
import lk.ijse.hostal.service.SuperBO;
import lk.ijse.hostal.service.custom.CustomBO;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
       /* Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        CustomBO bo = (CustomBO) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.CUSTOM);
        List<CustomDTO> list = bo.getList();
        for (CustomDTO customDTO : list) {
            System.out.println(customDTO.getStudentName());
        }


        transaction.commit();
        session.close();
*/

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/hostal/view/LoginForm.fxml"))));
        stage.setTitle("login");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
}
