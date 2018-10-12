package sample.controllerFiles.AdminDashBoard.TaskTab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TaskTabController {

    @FXML
    GridPane contentAreaTask;

    public void loadFxmlViewEditTask (ActionEvent event) throws IOException {
        ScrollPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/TaskTab/ViewEditTaskButton.fxml"));
        contentAreaTask.getChildren().setAll(newLoadedPane);
    }

    public void loadFxmlAddTask (ActionEvent event) throws IOException {
        ScrollPane newLoadedPane = FXMLLoader.load(getClass().getResource("/sample/fxmlFiles/AdminDashBoard/TaskTab/AddTaskButton.fxml"));
        contentAreaTask.getChildren().setAll(newLoadedPane);
    }



}
