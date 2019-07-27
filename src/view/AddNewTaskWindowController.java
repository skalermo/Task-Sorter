package view;

import application.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewTaskWindowController implements Initializable {

    // Instance of primary controller for passing data to it
    private MainWindowController mainWindowController;

    // These are for task's infos
    @FXML private TextField taskNameTextField;
    @FXML private Slider taskComplexitySlider;
    @FXML private TextField startDateTextField;
    @FXML private DatePicker taskEndDatePicker;
    @FXML private TextField durationTextField;

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
        if (taskEndDate.isBefore(LocalDate.now()))
            return;
        if (taskName != null && complexity != 0)
            newTask = new Task(taskName, complexity, LocalDate.now(), taskEndDate);

        if (newTask != null) {
            mainWindowController.storeNewTask(newTask);

            // Get a handle to this stage
            Node source = (Node)event.getSource();
            Stage thisStage = (Stage)source.getScene().getWindow();
            // Close the stage
            thisStage.close();

        }

    }

    /**
     * When in the text field number entered
     * the value of datePicker changes respectively
     */
    public void durationTextFieldOnKeyPressed()
    {
        int duration;
        try {
            duration = Integer.parseInt(durationTextField.getText());
            if (duration < 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            //todo Print message or something to print non-negative numbers only
            return;
        }
        taskEndDatePicker.setValue(LocalDate.now().plusDays(duration));
    }

    /**
     * When date picked in datePicker
     * the value of the durationTextField changes respectively
     */
    public void endDatePickerOnAction()
    {
        durationTextField.setText(Integer.toString((int)Duration.between(LocalDate.now().atStartOfDay(), taskEndDatePicker.getValue().atStartOfDay()).toDays()));
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
