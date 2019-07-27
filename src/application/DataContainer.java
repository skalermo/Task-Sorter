package application;

import java.io.Serializable;
import java.util.List;

class DataContainer implements Serializable {
    private List<Task> taskList;

    DataContainer(List<Task> taskList) {
        this.taskList = taskList;
    }

    List<Task> getTaskList() {
        return taskList;
    }
}
