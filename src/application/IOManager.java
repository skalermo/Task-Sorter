package application;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

public class IOManager {
    private FileChooser fileChooser;

    public IOManager(){
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter stfFilter = new FileChooser.ExtensionFilter("Set of Tasks Format (*.stf)", "*.stf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All files (*)", "*");
        fileChooser.getExtensionFilters().addAll(stfFilter, allFilter);
        File mapsDir = new File("src/application/data");
        if (mapsDir.exists())
            fileChooser.setInitialDirectory(mapsDir);
    }


    public void saveAs(Stage stage, List<Task> taskList) {

        DataContainer container = new DataContainer(taskList);
        fileChooser.setTitle("Save in");

        File file = fileChooser.showSaveDialog(stage);

        try {
            //new File("src/application/savedMaps").mkdir();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(container);
            oos.close();

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

    }

//    public List<List<Tile>> open() {
//        fileChooser.setTitle("Load from");
//        File file = fileChooser.showOpenDialog(stage);
//        try {
//
//            FileInputStream fis = new FileInputStream(file);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            MapContainer container = (MapContainer) ois.readObject();
//            ois.close();
//            return container.getTiles();
//
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        } catch (NullPointerException e) {
//            return null;
//        }
//
//    }

}
