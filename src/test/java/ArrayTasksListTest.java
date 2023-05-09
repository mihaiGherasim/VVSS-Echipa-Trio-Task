import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayTasksListTest {

    @Spy
    private ArrayTaskList tasks;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd(){
        Task t1 = new Task("Task1");
        tasks.add(t1);
        tasks.add(t1);
        tasks.add(t1);
        assert(tasks.getAll().size() == 3);

        Task t2 = new Task("t2");
        tasks.add(t2);
        assert (4 == tasks.getAll().size());
        Mockito.doNothing().when(tasks).add(t2);
        assert (4 == tasks.getAll().size());
        Mockito.verify(tasks, Mockito.times(3)).add(t1);
    }

    @Test
    public void testGetTask(){
        Task t1 = new Task("t1");
        Task t2 = new Task("t2");
        Task t3 = new Task("t3");
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        assert (tasks.getAll().size() == 3);
        Mockito.doThrow(IndexOutOfBoundsException.class).when(tasks).getTask(10);

        assertEquals(t1, tasks.getTask(0));
        assertThrows(IndexOutOfBoundsException.class, ()->tasks.getTask(10));
        assertThrows(IndexOutOfBoundsException.class, ()->tasks.getTask(9));

        Mockito.verify(tasks, Mockito.times(1)).getTask(0);
        Mockito.verify(tasks, Mockito.times(1)).getTask(10);
        Mockito.verify(tasks, Mockito.times(1)).getTask(9);
    }

}
