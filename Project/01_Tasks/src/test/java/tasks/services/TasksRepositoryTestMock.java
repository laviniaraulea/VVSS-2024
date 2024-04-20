package tasks.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;
import tasks.model.TasksRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TasksRepositoryTestMock {

    @InjectMocks
    private TasksRepository tasksRepository= Mockito.mock(TasksRepository.class);


    @Test
    void testAddTask() {
        Mockito.doNothing().when(tasksRepository).add(any());
        Task task = new Task("Test Task", new Date(2027, 4, 5));
        tasksRepository.add(task);
        Mockito.when(tasksRepository.size()).thenReturn(1);
        Mockito.verify(tasksRepository, times(1)).add(task);
        assertEquals(1, tasksRepository.size());
    }

    @Test
    public void testRemoveTask() {
        Mockito.doNothing().when(tasksRepository).add(any());

        Task task1 = new Task("Task 1", new Date(2027, 4, 5));
        Task task2 = new Task("Task 2", new Date(2027, 4, 5));
        tasksRepository.add(task1);
        tasksRepository.add(task2);

        Mockito.when(tasksRepository.size()).thenReturn(2);
        assertEquals(2, tasksRepository.size());

        tasksRepository.remove(task1);

        Mockito.verify(tasksRepository, times(1)).remove(task1);
        Mockito.verify(tasksRepository, never()).remove(task2);

        Mockito.when(tasksRepository.size()).thenReturn(1);
        assertEquals(1, tasksRepository.size());
    }
}