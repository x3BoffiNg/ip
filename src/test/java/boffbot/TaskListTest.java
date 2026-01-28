package boffbot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_taskCountIncreases() {
        TaskList taskList = new TaskList();
        Task t1 = new Todo("read book");

        taskList.add(t1);

        assertEquals(1, taskList.size());
        assertEquals(t1, taskList.get(0));
    }

    @Test
    public void removeTask_taskIsRemoved() {
        TaskList taskList = new TaskList();
        Task t1 = new Todo("read book");
        Task t2 = new Todo("write code");

        taskList.add(t1);
        taskList.add(t2);

        Task removed = taskList.remove(0);

        assertEquals(t1, removed);
        assertEquals(1, taskList.size());
        assertEquals(t2, taskList.get(0));
    }
}
