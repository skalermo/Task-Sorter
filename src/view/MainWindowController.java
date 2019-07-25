package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // These are for Menu Items
    @FXML private MenuBar menuBar;

    // These are management buttons on the left
    @FXML private Button addNewTaskButton;

    // These are for ScrollPane and InfoLabels
    @FXML private ScrollPane scrollPane;
    @FXML private Label newFileLabel;
    @FXML private Label openFileLabel;

    // This is for InfoLabels on the bottom of the scene
    @FXML private Label currentDate;


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
        scrollPane.setDisable(false);
        scrollPane.setOpacity(1.0);
        newFileLabel.setDisable(true);
        newFileLabel.setOpacity(0.0);
        openFileLabel.setDisable(true);
        openFileLabel.setOpacity(0.0);
    }

    /**
     * This method disables and hides ScrollPane
     * and shows InfoLabels
     */
    public void closeMenuItemAction()
    {
        if (scrollPane.isDisabled())
            return;
        scrollPane.setDisable(true);
        scrollPane.setOpacity(0.0);
        newFileLabel.setDisable(false);
        newFileLabel.setOpacity(1.0);
        openFileLabel.setDisable(false);
        openFileLabel.setOpacity(1.0);
    }

    public void addNewTaskButtonAction() throws IOException
    {
        Stage addNewTaskStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddNewTaskWindow.fxml"));
        addNewTaskStage.setTitle("Add new Task");
        addNewTaskStage.setScene(new Scene(root, 450, 350));
        addNewTaskStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCurrentDateLabel();
    }

    /**
     * This method sets current date to the Label
     */
    private void setCurrentDateLabel()
    {
        currentDate.setText(LocalDate.now().toString());
    }


}
