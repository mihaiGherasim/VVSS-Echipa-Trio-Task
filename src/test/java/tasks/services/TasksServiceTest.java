package tasks.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasksServiceTest {
    @Mock
    ArrayTaskList tasks = new ArrayTaskList();
    TasksService service;
    @Mock
    TasksOperations tasksOperations;
    Task task;

    @BeforeEach
    public void setUp(){
        tasks = Mockito.mock(ArrayTaskList.class);
        service = new TasksService(tasks);
        task = Mockito.mock(Task.class);
    }

    @Test
    public void testAddTask_invalidTask(){
        task.setTitle("ThisIsAValidTitle");
        Mockito.doThrow(IllegalArgumentException.class).when(task).getTitle();
        assertThrows(IllegalArgumentException.class, ()->service.addTask(task));
    }

    @Test
    public void testAddTask_validTask(){
        Task validTask = new Task("ValidTitle");
        assertEquals(0, service.getTasks().size());
        Mockito.doNothing().when(tasks).add(validTask);
        service.addTask(validTask);
        assertEquals(0, service.getTasks().size());
    }
}
