package view;

import application.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewTaskWindowController implements Initializable {

    // These are for task's infos
    @FXML private TextField taskNameTextField;
    @FXML private Slider taskComplexitySlider;
    @FXML private TextField startDateTextField;
    @FXML private DatePicker taskEndDatePicker;

    /**
     * This method gets data from fields
     * and makes new Task object
     */
    public void applyButtonAction()
    {
        Task task = null;
        String taskName = taskNameTextField.getText();
        int complexity = (int)taskComplexitySlider.getValue();
        LocalDate taskEndDate = taskEndDatePicker.getValue();
        if (taskName != null && complexity != 0 && taskEndDate != null)
            task = new Task(taskName, complexity, LocalDate.now(), taskEndDate);
        System.out.println(task);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startDateTextField.setText(LocalDate.now().toString());
    }
}
