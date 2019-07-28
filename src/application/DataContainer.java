package application;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable {
    private List<Task> taskList;
    private LocalDate lastSaveDate;

    public DataContainer() {
        taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public LocalDate getLastSaveDate() {
        return lastSaveDate;
    }

    public void setLastSaveDate(LocalDate lastSaveDate) {
        this.lastSaveDate = lastSaveDate;
    }
}
