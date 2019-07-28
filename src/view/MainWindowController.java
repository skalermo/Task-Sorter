package view;

import application.DataContainer;
import application.IOManager;
import application.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // This is IOManager instance, allows operations with files
    private IOManager ioManager;

    // This object contains all required information about tasks
    // and other stuff too
    private DataContainer dataContainer;

    // These are for Menu Items
    @FXML private MenuBar menuBar;

    // Configure the tableView
    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, Double> taskPriorityColumn;
    @FXML private TableColumn<Task, String> taskNameColumn;
    @FXML private TableColumn<Task, Integer> taskComplexityColumn;
    @FXML private TableColumn<Task, Integer> daysLeftColumn;
    @FXML private TableColumn<Task, LocalDate> taskStartDateColumn;
    @FXML private TableColumn<Task, LocalDate> taskEndDateColumn;

    // These are management elements on the left
    @FXML private VBox buttonsVBox;
    @FXML private Button addNewTaskButton;

    // These are elements of StackPane
    @FXML private ScrollPane scrollPane;
    @FXML private Label newFileLabel;
    @FXML private Label openFileLabel;
    @FXML private StackPane stackPane;

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
        dataContainer.setLastSaveDate(LocalDate.now());
        ioManager.saveAs((Stage)menuBar.getScene().getWindow(), dataContainer);
    }

    /**
     * This method loads list of tasks
     * and if necessary enables scrollPane with tableView
     */
    public void openMenuItemAction()
    {
        // todo To check if dataContainer is null (otherwise some important data may be lost)
        dataContainer = ioManager.open((Stage)menuBar.getScene().getWindow());
        if (dataContainer == null)
            return;

        // Updates priorities if last saveTime was not today
        if (!dataContainer.getLastSaveDate().isEqual(LocalDate.now()))
            for (Task task : dataContainer.getTaskList())
                task.calcPriority();

        changeTableViewAndLabelsVisibility();
        addNewTaskButton.setDisable(false);
        taskTableView.getItems().clear();
        taskTableView.getItems().addAll(dataContainer.getTaskList());
    }

    /**
     * This method creates new Stage
     * where one can create a task;
     * passes this controller to the new one
     * which provides ability to pass data between them;
     * adds key listeners to the new Stage's scene
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

        // Adding key listener
        addNewTaskStage.getScene().setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE)
                addNewTaskStage.close();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ioManager = new IOManager();
        dataContainer = new DataContainer();

        setCurrentDateLabel();

        // Set up the columns in the table

        taskPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        taskComplexityColumn.setCellValueFactory(new PropertyValueFactory<>("taskComplexity"));
        daysLeftColumn.setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        taskStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        taskEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }

    /**
     * This method gets created Task
     * then stores it into the taskList and the TableView
     */
    void storeNewTask(Task newTask)
    {
        dataContainer.getTaskList().add(newTask);
        taskTableView.getItems().add(newTask);
    }

}
