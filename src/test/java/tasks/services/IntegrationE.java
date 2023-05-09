package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationE {
    private ArrayTaskList taskList;
    private TasksService tasksService;

    @BeforeEach
    public void setUp(){
        taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    public void testAddValidTask() {
        Task task = new Task("Description", "CursVVSS", new Date(1680325200000L), new Date(1680325200000L), new Date(1682838000000L), 86400, true);
        int size = tasksService.getTasks().size();
        tasksService.addTask(task);
        assertEquals(size+1, tasksService.getTasks().size());
        assertEquals(tasksService.getTasks().getTask(size), task);
    }

    @Test
    public void testAddInvalidTask(){
        Task task = new Task("Description", "", new Date(1680325200000L), new Date(1680325200000L), new Date(1682838000000L), 86400, true);
        int size = tasksService.getTasks().size();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(task));
        assertEquals("Invalid arguments!!!", illegalArgumentException.getMessage());
        assertEquals(size, tasksService.getTasks().size());
    }
}
