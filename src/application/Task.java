package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private SimpleStringProperty taskName;
    private String description;
    private SimpleIntegerProperty taskComplexity;
    private LocalDate startDate;
    private LocalDate endDate;

    public Task(String taskName, int taskComplexity, LocalDate startDate, LocalDate endDate) {
        this.taskName = new SimpleStringProperty(taskName);
        this.taskComplexity = new SimpleIntegerProperty(taskComplexity);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskName() {
        return taskName.get();
    }

    public SimpleStringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public int getTaskComplexity() {
        return taskComplexity.get();
    }

    public SimpleIntegerProperty taskComplexityProperty() {
        return taskComplexity;
    }

    public void setTaskComplexity(int taskComplexity) {
        this.taskComplexity.set(taskComplexity);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
