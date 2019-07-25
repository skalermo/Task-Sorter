package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    // These are for Menu Items
    @FXML private MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method closes the stage
     */
    public void closeMenuItemAction()
    {
        // This line gets the Stage information
        Stage window = (Stage)menuBar.getScene().getWindow();
        // Close stage
        window.close();
    }

}
