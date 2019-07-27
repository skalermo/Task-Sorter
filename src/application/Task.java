package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class Task implements Serializable {
    private SimpleStringProperty taskName;
    private String description;
    private SimpleIntegerProperty taskComplexity;
    private LocalDate startDate;
    private LocalDate endDate;
    private transient double priority;

    /**
     * Implemented method, ensures correct writing to file
     */
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.writeObject(taskName.get());
        out.writeObject(description);
        out.writeInt(taskComplexity.getValue());
        out.writeObject(startDate);
        out.writeObject(endDate);
    }

    /**
     * Implemented method, ensures correct reading from file
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        taskName = new SimpleStringProperty((String)in.readObject());
        description = (String)in.readObject();
        taskComplexity = new SimpleIntegerProperty(in.readInt());
        startDate = (LocalDate)in.readObject();
        endDate = (LocalDate)in.readObject();
    }

    public Task(String taskName, int taskComplexity, LocalDate startDate, LocalDate endDate) {
        this.taskName = new SimpleStringProperty(taskName);
        this.taskComplexity = new SimpleIntegerProperty(taskComplexity);
        this.startDate = startDate;
        this.endDate = endDate;
        this.calcPriority();
    }

    /**
     * Calculates task's current priority,
     * 0 - if endDate == today
     */
    public void calcPriority()
    {
        // Days between endDate and today
        int dayDifference = (int)Duration.between(endDate, LocalDate.now()).toDays();

        priority = (double)dayDifference / taskComplexity.get();
    }

    // Some of the getters and setters are used by JavaFX
    // Do not edit

    public String getTaskName() {
        return taskName.get();
    }

    public SimpleStringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
