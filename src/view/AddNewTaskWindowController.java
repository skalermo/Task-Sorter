package view;

import application.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewTaskWindowController implements Initializable {

    // Instance of primary controller for passing data to it
    MainWindowController mainWindowController;

    // These are for task's infos
    @FXML private TextField taskNameTextField;
    @FXML private Slider taskComplexitySlider;
    @FXML private TextField startDateTextField;
    @FXML private DatePicker taskEndDatePicker;

    /**
     * This method gets data from fields
     * and makes new Task object
     * then if input is valid passes it to primary controller
     * and closes the stage
     */
    public void applyButtonAction(ActionEvent event)
    {
        Task newTask = null;
        String taskName = taskNameTextField.getText();
        int complexity = (int)taskComplexitySlider.getValue();
        LocalDate taskEndDate = taskEndDatePicker.getValue();
        if (taskName != null && complexity != 0 && taskEndDate != null)
            newTask = new Task(taskName, complexity, LocalDate.now(), taskEndDate);

        if (newTask != null) {
            mainWindowController.setNewTask(newTask);

            // Get a handle to this stage
            Node source = (Node)event.getSource();
            Stage thisStage = (Stage)source.getScene().getWindow();
            // Close the stage
            thisStage.close();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDateTextField.setText(LocalDate.now().toString());
    }

    /**
     * This method gets controller in order to pass to it some data
     */
    void setPrimaryController(MainWindowController controller)
    {
        mainWindowController = controller;
    }
}
