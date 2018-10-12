package sample.controllerFiles.EmployeeDashBoard.ProjectTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ProjectTabController {

    @FXML
    GridPane contentAreaProject;

    public void loadFxmlViewProject (ActionEvent event) throws IOException {
        GridPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EmployeeDashBoard/ProjectTab/ViewProjectButton.fxml"));
        contentAreaProject.getChildren().setAll(newLoadedPane);
    }


}
