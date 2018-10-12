package sample.controllerFiles.AdminDashBoard.ProjectTab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectTabController{

    @FXML
    GridPane contentAreaProject;

    public void loadFxmlViewEditProject (ActionEvent event) throws IOException {
        GridPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/ProjectTab/ViewEditProjectButton.fxml"));
        contentAreaProject.getChildren().setAll(newLoadedPane);
    }

    public void loadFxmlAddProject () throws IOException {
        GridPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/ProjectTab/AddProjectButton.fxml"));
        contentAreaProject.getChildren().setAll(newLoadedPane);
    }
    @FXML
    public void initialize()
    {
        ActionEvent e = new ActionEvent();
        try {
            loadFxmlViewEditProject(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
