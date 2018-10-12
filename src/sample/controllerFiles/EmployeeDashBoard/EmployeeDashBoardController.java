package sample.controllerFiles.EmployeeDashBoard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDashBoardController {
    @FXML
    private Button employeeLogOut;


    public void loadFxmlLoginPage (ActionEvent event) throws IOException {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/LoginPage.fxml"));

            Stage stageLogin=(Stage)employeeLogOut.getScene().getWindow();

            Stage stage=new Stage();
            stage.setTitle("Follow-Up Tracker");
            stage.setScene(new Scene(root1, 1350, 680));
            stage.setResizable(false);
            stage.show();

            stageLogin.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
