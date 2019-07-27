package view;

import application.IOManager;
import application.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // todo Wrap all used data in DataContainer

    // This is IOManager instance, allows operations with files
    private IOManager ioManager;

    // This is list which contains all tasks
    private List<Task> taskList;

    // These are for Menu Items
    @FXML private MenuBar menuBar;

    // Configure the tableView
    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> taskNameColumn;
    @FXML private TableColumn<Task, Integer> taskComplexityColumn;
    @FXML private TableColumn<Task, LocalDate> taskStartDateColumn;
    @FXML private TableColumn<Task, LocalDate> taskEndDateColumn;

    // These are management buttons on the left
    @FXML private Button addNewTaskButton;

    // These are for ScrollPane and InfoLabels
    @FXML private ScrollPane scrollPane;
    @FXML private Label newFileLabel;
    @FXML private Label openFileLabel;

    // This is for InfoLabels on the bottom of the scene
    @FXML private Label currentDate;


    /**
     * This method sets current date to the Label
     */
    private void setCurrentDateLabel()
    {
        currentDate.setText(LocalDate.now().toString());
    }

    private void changeTableViewAndLabelsVisibility()
    {
        scrollPane.setDisable(!scrollPane.isDisabled());
        scrollPane.setOpacity(1.0 - scrollPane.getOpacity());
        newFileLabel.setDisable(!newFileLabel.isDisabled());
        newFileLabel.setOpacity(1.0 - newFileLabel.getOpacity());
        openFileLabel.setDisable(!openFileLabel.isDisabled());
        openFileLabel.setOpacity(1.0 - openFileLabel.getOpacity());
    }

    /**
     * This method closes the stage
     */
    public void quitMenuItemAction()
    {
        // This line gets the Stage information
        Stage window = (Stage)menuBar.getScene().getWindow();
        // Close stage
        window.close();
    }


    /**
     * This method enables and shows ScrollPane which contain TableView
     * also hides InfoLabels
     */
    public void newMenuItemAction()
    {
        if (!scrollPane.isDisabled())
            return;
        changeTableViewAndLabelsVisibility();
        addNewTaskButton.setDisable(false);
    }

    /**
     * This method disables and hides ScrollPane
     * and shows InfoLabels
     */
    public void closeMenuItemAction()
    {
        if (scrollPane.isDisabled())
            return;
        changeTableViewAndLabelsVisibility();
        addNewTaskButton.setDisable(true);
    }

    /**
     * This method saves list of tasks using the stage and ioManager
     */
    public void saveAsMenuItemAction()
    {
        ioManager.saveAs((Stage)menuBar.getScene().getWindow(), taskList);
    }

    /**
     * This method loads list of tasks
     * and if necessary enables scrollPane with tableView
     */
    public void openMenuItemAction()
    {
        taskList = ioManager.open((Stage)menuBar.getScene().getWindow());
        if (taskList == null)
            return;

        // todo Add check if today == day when file was saved last time
        // Updates priorities
        for (Task task : taskList)
            task.calcPriority();

        changeTableViewAndLabelsVisibility();
        addNewTaskButton.setDisable(false);
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(taskList);
    }

    /**
     * This method creates new Stage
     * where one can create a task;
     * Also passes this controller to the new one
     * which provides ability to pass data between them
     */
    public void addNewTaskButtonAction() throws IOException
    {
        Stage addNewTaskStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewTaskWindow.fxml"));
        Parent root = loader.load();
        addNewTaskStage.setTitle("Add new Task");
        addNewTaskStage.setScene(new Scene(root, 450, 350));
        addNewTaskStage.show();

        // Passing this controller to the new one
        AddNewTaskWindowController controller = loader.getController();
        controller.setPrimaryController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ioManager = new IOManager();
        taskList = new ArrayList<>();
        setCurrentDateLabel();

        // Set up the columns in the table
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        taskComplexityColumn.setCellValueFactory(new PropertyValueFactory<>("taskComplexity"));
        taskStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        taskEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }

    /**
     * This method gets created Task
     * then stores it into the taskList and the TableView
     */
    void storeNewTask(Task newTask)
    {
        taskList.add(newTask);
        taskTableView.getItems().add(newTask);
    }

}
