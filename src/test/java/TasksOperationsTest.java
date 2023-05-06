import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskList;
import tasks.model.TasksOperations;
import tasks.persistence.TaskRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TasksOperationsTest {

    public static File savedTasksFile = new File("data/tasks.txt");
    private ArrayTaskList tasks = new ArrayTaskList();
    TasksOperations tasksOperations;

    @BeforeEach
    public void setUp() {
        tasks.add(new Task("Description", "Curs", new Date(1680325200000L), new Date(1680325200000L), new Date(1682838000000L), 86400, true));
        tasks.add(new Task("Description", "Laborator", new Date(1680411600000L), new Date(1680411600000L), new Date(1689145200000L), 36000, true));
        tasks.add(new Task("Description", "Colocviu", new Date(1686632400000L), new Date(1686632400000L), new Date(1686639600000L), 60, true));
        tasks.add(new Task("Description", "Seminar", new Date(1677650400000L), new Date(1677650400000L), new Date(1677657600000L), 604800, true));
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(tasks.getAll()));
    }

    @Test
    public void test01(){
        Iterable<Task> incoming = tasksOperations.incoming(new Date(1680411600000L), new Date(1689145200000L));

        ArrayList<Task> returnedTasks = new ArrayList<>();
        for (Task task : incoming) {
            returnedTasks.add(task);
        }

        assertEquals(3, returnedTasks.size());
    }

    @Test
    public void test02(){
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> tasksOperations.incoming(new Date(1689145200000L), new Date(1680411600000L)));
        assertEquals("Start date is after end date", runtimeException.getMessage());
    }

    @Test
    public void test03(){
        Iterable<Task> incoming = tasksOperations.incoming(new Date(1689145200000L), new Date(1699145200000L));

        assertNull(incoming);
    }
}
