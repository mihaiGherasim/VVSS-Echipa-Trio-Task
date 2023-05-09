import org.junit.jupiter.api.*;
import tasks.controller.NewEditController;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;

import static javafx.collections.FXCollections.observableArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NewEditControllerTest {

    static NewEditController controller = new NewEditController();
    Date startDate;
    Date endDate;
    String title;
    String description;
    Date time;
    boolean active;
    int interval;
    Calendar calendar;

    @BeforeEach
    public void setUp(){
        title = "Title";
        description = "description";
        time = new Date(1670707200000L);
        active = true;
        interval = 30;

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 12);
        endDate = calendar.getTime();
        controller.setTasksList(observableArrayList());
    }

    @AfterAll
    public static void tearDown(){
        controller = null;
    }

    @Test
    @RepeatedTest(3)
    public void testAddTaskTC1_ECP(){
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);

        assertEquals(1, controller.getTasksList().size());
    }

    @Test
    @Disabled
    public void testAddTaskTC2_ECP(){
        description = "";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid description!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC3_ECP() {
        calendar.clear();
        calendar.set(Calendar.DATE, -2);
        endDate = calendar.getTime();

        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid end time!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC4_ECP() {
        calendar.clear();
        calendar.set(Calendar.DATE, -1);
        endDate = calendar.getTime();

        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid end time!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC5_ECP() {
        title = "";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid title!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC6_ECP() {
        title = "Acest titlu contine mai mult de 50 de caractere(61 caractere)";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid title!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC1_BVA(){
        title = "";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid title!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC2_BVA(){
        title = "T";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);

        assertEquals(1, controller.getTasksList().size());
    }

    @Test
    public void testAddTaskTC3_BVA() {
        title = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWX";
        assertEquals(50, title.length());
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);
        assertEquals(1, controller.getTasksList().size());
    }

    @Test
    public void testAddTaskTC4_BVA(){
        title = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXZ";
        assertEquals(51, title.length());
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid title!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC5_BVA() {
        title = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVW";
        assertEquals(49, title.length());
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);
        assertEquals(1, controller.getTasksList().size());
    }

    @Test
    public void testAddTaskTC6_BVA(){
        description = "";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> controller.addTask(taskToBeAdded));
        assertEquals("Invalid description!!!", runtimeException.getMessage());
    }

    @Test
    public void testAddTaskTC7_BVA(){
        description = "D";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);
        assertEquals(1, controller.getTasksList().size());
    }

    @Test
    public void testAddTaskTC8_BVA(){
        description = "D2";
        Task taskToBeAdded = new Task(description, title, time, startDate, endDate, interval, active);
        controller.addTask(taskToBeAdded);
        assertEquals(1, controller.getTasksList().size());
    }
}
