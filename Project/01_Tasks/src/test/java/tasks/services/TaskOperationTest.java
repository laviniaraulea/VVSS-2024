package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskOperationTest {

    @Test
    void testInvalidInputDates2() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.add(new Task("Task 1", new Date(2027, 4, 5)));
        TasksOperations tasksOperations = new TasksOperations(tasks);
        assertThrows(IllegalArgumentException.class, () -> tasksOperations.incoming(null,new Date(2024, 4, 1)));
    }
    @Test
    void testInvalidInputDates3() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.add(new Task("Task 1", new Date(2027, 4, 5)));
        TasksOperations tasksOperations = new TasksOperations(tasks);
        assertThrows(IllegalArgumentException.class, () -> tasksOperations.incoming(new Date(2025, 4, 5),null));
    }
    @Test
    void testEmptyTaskList() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        TasksOperations tasksOperations = new TasksOperations(tasks);
        Iterable<Task> incomingTasks = tasksOperations.incoming(new Date(2025, 4, 5),new Date(2026, 4, 5));
        assertNotNull(incomingTasks);
    }

    @Test
    void testTaskListWithNoRecurrentTask() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        tasks.add(new Task("Task 1", new Date(2027, 4, 5)));
        TasksOperations tasksOperations = new TasksOperations(tasks);
        Iterable<Task> incomingTasks = tasksOperations.incoming(new Date(2025, 4, 5),new Date(2026, 4, 5));
        assertNotNull(incomingTasks);
    }

    @Test
    void testTaskListWithRecurrentTask() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        Task task = new Task("Task 2",new Date(2025, 4, 5),new Date(2028, 4, 5),1);
        task.setActive(true);
        tasks.add(task);
        TasksOperations tasksOperations = new TasksOperations(tasks);
        Iterable<Task> incomingTasks = tasksOperations.incoming(new Date(2025, 3, 5),new Date(2026, 4, 5));
        List<Task> incomingTaskList = new ArrayList<>();
        incomingTasks.forEach(incomingTaskList::add);

        assertEquals(1, incomingTaskList.size());
    }
}
