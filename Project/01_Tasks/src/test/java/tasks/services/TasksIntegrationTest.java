package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.Task;
import tasks.model.TasksRepository;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TasksIntegrationTest {
    @InjectMocks
    private TasksService tasksService;

    @Mock
    private TasksRepository tasksRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTaskIntegration() {
        // Create a task
        Task task = new Task("Test Task", new Date(2027, 4, 5));

        // Mock behavior of tasksRepository
        doNothing().when(tasksRepository).add(any(Task.class));

        // Call the service method to add the task
        tasksService.saveTask(task);

        // Verify that the task was added to the repository
        verify(tasksRepository, times(1)).add(task);
    }

    @Test
    void testRemoveTaskIntegration() {
        // Create a task
        Task task = new Task("Test Task", new Date(2027, 4, 5));

        // Mock behavior of tasksRepository
        when(tasksRepository.size()).thenReturn(1);

        // Call the service method to remove the task
        tasksService.removeTask(task);

        // Verify that the task was removed from the repository
        verify(tasksRepository, times(1)).remove(task);
    }
}
