package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.Task;
import tasks.model.TasksRepository;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TasksServiceTestMock {

    @InjectMocks
    private TasksService tasksService;

    @Mock
    private TasksRepository tasksRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveTask_ValidParameters_TaskAddedToListAndRepository() {
        ObservableList<Task> tasksList = FXCollections.observableArrayList();
        String title = "Test Task";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600 * 1000);
        int interval = 3600;

        Mockito.doNothing().when(tasksRepositoryMock).add(Mockito.any(Task.class));

        tasksService.saveTask(tasksList, title, endDate, startDate, interval);

        assertEquals(1, tasksList.size());
        Task savedTask = tasksList.get(0);
        assertNotNull(savedTask);
        assertEquals(title, savedTask.getTitle());
        assertEquals(startDate, savedTask.getStartTime());
        assertEquals(endDate, savedTask.getEndTime());
        assertEquals(interval, savedTask.getRepeatInterval());

        Mockito.verify(tasksRepositoryMock,times(1)).add(savedTask);
    }

    @Test
    void saveTask_EmptyTitle_ExceptionThrown() {
        ObservableList<Task> tasksList = FXCollections.observableArrayList();
        String title = "";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600 * 1000);
        int interval = 3600;

        assertThrows(IllegalArgumentException.class, () -> tasksService.saveTask(tasksList, title, endDate, startDate, interval));
    }
}
