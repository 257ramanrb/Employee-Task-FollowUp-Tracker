package sample.controllerFiles.AdminDashBoard.EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class EmployeeTabController {

    @FXML
    GridPane contentAreaEmployee;

    public void loadFxmlViewEditEmployee (ActionEvent event) throws IOException {
        GridPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/EmployeeTab/ViewEditEmployeeButton.fxml"));
        contentAreaEmployee.getChildren().setAll(newLoadedPane);
    }

}
