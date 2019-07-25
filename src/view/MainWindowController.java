package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // These are for Menu Items
    @FXML private MenuBar menuBar;

    // These are for ScrollPane and InfoLabels
    @FXML private ScrollPane scrollPane;
    @FXML private Label newFileLabel;
    @FXML private Label openFileLabel;


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
