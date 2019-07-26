package application;

import java.io.Serializable;
import java.util.List;

public class DataContainer implements Serializable {
    private List<Task> taskList;

    public DataContainer(List<Task> taskList) {
        this.taskList = taskList;
    }
}
