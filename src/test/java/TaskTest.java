import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.controller.TaskRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    private String description;
    private String title;
    private Date time;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;

    @BeforeEach
    public void setUp(){
        description="description";
        title="title";
        interval=10;
        active=true;
        time=new Date(1670707200000L);
        start=new Date(1670707200000L);
        end=new Date(1670707200000L);
        task = new Task(description, title, time, start, end, interval, active);
    }

    @Test
    public void testGetDescription() {
        assertEquals("description", task.getDescription());
    }

    @Test
    public void setDescription() {
        String newDescription = "description1";
        task.setDescription(newDescription);
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void testGetTime() {
        assertEquals(new Date(1670707200000L), task.getTime());
    }

    @Test
    public void testSetTime() {
        Date newDate = new Date(1670807200000L);
        task.setTime(newDate);
        assertEquals(new Date(1670807200000L), task.getTime());
    }

    @Test
    public void testGetInterval() {
        assertEquals(10, task.getRepeatInterval());
    }

    @Test
    public void testSetInterval() {
        int newInterval = 11;
        task.setInterval(newInterval);
        assertEquals(newInterval, task.getRepeatInterval());
    }

    @Test
    public void testIsActive() {
        assertTrue(task.isActive());
    }

    @Test
    public void testSetInactive() {
        boolean isActive = false;
        task.setActive(isActive);
        assertFalse(task.isActive());
    }
}