package sample.controllerFiles.EmployeeDashBoard.EmployeeTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class EmployeeTabController {

    @FXML
    GridPane contentAreaEmployee;

    public void loadFxmlViewEditEmployee (ActionEvent event) throws IOException {
        GridPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EmployeeDashBoard/EmployeeTab/ViewEditEmployeeButton.fxml"));
        contentAreaEmployee.getChildren().setAll(newLoadedPane);
    }

}
