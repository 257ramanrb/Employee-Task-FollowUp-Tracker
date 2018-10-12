package sample.controllerFiles.AdminDashBoard;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import sample.controllerFiles.AdminDashBoard.ProjectTab.ProjectTabController;

import java.io.IOException;

public class AdminDashBoardController {

    @FXML
    private Button adminLogOut;
    @FXML
    private TabPane tabPane;


    public void loadFxmlLoginPage(ActionEvent event) throws IOException {
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/LoginPage.fxml"));

            Stage stageLogin = (Stage) adminLogOut.getScene().getWindow();

            Stage stage = new Stage();
            stage.setTitle("Follow-Up Tracker");
            stage.setScene(new Scene(root1, 1350, 680));
            stage.setResizable(false);
            stage.show();

            stageLogin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zoomHandle()
    {

    }

}
