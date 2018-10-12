package sample.controllerFiles.EmployeeDashBoard.TaskTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class TaskTabController {


    @FXML
    GridPane contentAreaTask;

    public void loadFxmlViewEditTask (ActionEvent event) throws IOException {
        ScrollPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/EmployeeDashBoard/TaskTab/ViewEditTaskButton.fxml"));
        contentAreaTask.getChildren().setAll(newLoadedPane);
    }

}
