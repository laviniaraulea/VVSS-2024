package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import tasks.model.TasksRepository;
import tasks.model.Task;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {

    File savedTasksFile = new File("data/test/tasks.txt");
    TasksRepository savedTasksList = new TasksRepository();
    private static ObservableList<Task> tasksList;

    @BeforeEach
    void setUp() throws IOException {
        // Load tasks from file if file exists
        if (savedTasksFile.exists()) {
            TaskIO.readBinary(savedTasksList, savedTasksFile);
        }
    }

    @AfterAll
    static void tearDownClass() {
        tasksList.clear();
    }

    @BeforeAll
    static void setUpClass() {
        tasksList = FXCollections.observableArrayList();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Test saveTask method with valid parameters")
    void saveTask() {
        // Create an ObservableList for tasks
        ObservableList<Task> tasksList = FXCollections.observableArrayList();

        // Create instance of TasksService
        TasksService service = new TasksService(savedTasksList);

        // Define task details
        String title = "10";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600 * 1000); // 1 hour after start date
        int interval = 20; // 20 minutes interval

        // Save the task
        service.saveTask(tasksList, title, endDate, startDate, interval);

        // Check if the task has been added to the list
        assertEquals(1, tasksList.size());
        Task savedTask = tasksList.get(0);
        assertEquals(title, savedTask.getTitle());
        assertEquals(startDate, savedTask.getStartTime());
        assertEquals(endDate, savedTask.getEndTime());
        assertEquals(interval, savedTask.getRepeatInterval());

        // Define task details
        title = "1";
        startDate = new Date();
        endDate = new Date(startDate.getTime() + 3600 * 1000); // 1 hour after start date
        interval = 1; // 60 minutes interval

        // Save the task
        service.saveTask(tasksList, title, endDate, startDate, interval);

        // Check if the task has been added to the list
        assertEquals(2, tasksList.size());
        savedTask = tasksList.get(1);
        assertEquals(title, savedTask.getTitle());
        assertEquals(startDate, savedTask.getStartTime());
        assertEquals(endDate, savedTask.getEndTime());
        assertEquals(interval, savedTask.getRepeatInterval());
    }

    @Test
    @DisplayName("Test saveTask method with empty title")
    void saveTask_EmptyTitle_IllegalArgumentExceptionThrown() {
        // Arrange
        TasksService service = new TasksService(savedTasksList);
        String title = "";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600 * 1000); // 1 hour after start date
        int interval = 20; // 60 minutes interval

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveTask(tasksList, title, endDate, startDate, interval);
        });
        assertEquals("The title should not be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Test saveTask method interval wrong")
    void saveTask_NewInterval_IllegalArgumentExceptionThrown() {
        // Arrange
        TasksService service = new TasksService(savedTasksList);
        String title = "titlu";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600 * 1000); // 1 hour after start date
        int interval = 0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.saveTask(tasksList, title, endDate, startDate, interval);
        });

    }


}
