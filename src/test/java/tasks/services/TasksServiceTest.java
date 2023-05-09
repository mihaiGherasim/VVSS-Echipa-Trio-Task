package tasks.services;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import tasks.model.ArrayTaskList;
import tasks.model.LinkedTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;
import tasks.services.TasksService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TasksServiceTest {
    @Mock
    ArrayTaskList tasks = new ArrayTaskList();
    TasksService service;
    @Mock
    TasksOperations tasksOperations;
    Task task;

    @BeforeEach
    public void setUp(){
        //tasks = Mockito.mock(ArrayTaskList.class);
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
