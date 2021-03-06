package application;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class IOManager {
    private FileChooser fileChooser;

    public IOManager(){
        new File("src/application/.data").mkdir();
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter stfFilter = new FileChooser.ExtensionFilter("Set of Tasks Format (*.stf)", "*.stf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All files (*)", "*");
        fileChooser.getExtensionFilters().addAll(stfFilter, allFilter);
        File mapsDir = new File("src/application/.data");
        if (mapsDir.exists())
            fileChooser.setInitialDirectory(mapsDir);
    }


    /**
     * This method saves data to a chosen file
     * @param stage Is where saveDialog pops up
     * @param dataContainer Data to be saved
     */
    public void saveAs(Stage stage, DataContainer dataContainer) {

        fileChooser.setTitle("Save in");

        File file = fileChooser.showSaveDialog(stage);

        try {

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dataContainer);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException ignored) {
        }

    }

    /**
     * Loads data from a chosen file
     * @param stage is where saveDialog pops up
     * @return if success returns list of tasks, otherwise null
     */
    public DataContainer open(Stage stage) {
        fileChooser.setTitle("Load from");
        File file = fileChooser.showOpenDialog(stage);
        try {

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataContainer dataContainer = (DataContainer) ois.readObject();
            ois.close();
            return dataContainer;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException ignored) {
            return null;
        }
    }

}
