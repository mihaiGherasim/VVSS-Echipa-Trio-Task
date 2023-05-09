package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationR {
    private ArrayTaskList taskList;
    private TasksService tasksService;

    @BeforeEach
    public void setUp(){
        taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    public void testAddValidTask(){
        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getTitle()).thenReturn("mockedTitle");
        int size = tasksService.getTasks().size();
        tasksService.addTask(task);
        assertEquals(size+1, tasksService.getTasks().size());
    }

    @Test
    public void testAddInvalidTask(){
        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getTitle()).thenReturn("t1");
        int size = tasksService.getTasks().size();
        assertThrows(IllegalArgumentException.class, ()->tasksService.addTask(task));
        assertEquals(size, tasksService.getTasks().size());
    }
}
