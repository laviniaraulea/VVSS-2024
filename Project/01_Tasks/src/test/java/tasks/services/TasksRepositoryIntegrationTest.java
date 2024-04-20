package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.Task;
import tasks.model.TasksRepository;
import tasks.services.TasksService;

import java.util.Date;

import static org.mockito.Mockito.*;

class TasksRepositoryIntegrationTest {
    @InjectMocks
    private TasksService tasksService;

    @Mock
    private TasksRepository tasksRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        tasksService.setTasks(tasksRepositoryMock);
    }

    @Test
    void testAddTaskIntegrationWithTasksRepository() {
        Task task = mock(Task.class);
        when(task.getTitle()).thenReturn("Title");
        when(task.getEndTime()).thenReturn(new Date(2021,2,20));
        when(task.getStartTime()).thenReturn(new Date(2020,2,20));
        when(task.getRepeatInterval()).thenReturn(2);

        tasksService.saveTask(task);

        verify(tasksRepositoryMock, times(1)).add(task);
    }

    @Test
    void testRemoveTaskIntegrationWithTasksRepository() {
        Task task = mock(Task.class);
        tasksService.removeTask(task);
        verify(tasksRepositoryMock, times(1)).remove(task);
    }
}
