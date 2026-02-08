package boffbot;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {}

    /**
     * Constructs a TaskList with existing tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void add(Task t) {
        assert t != null : "Task to add should not be null";
        tasks.add(t);
    }


    /**
     * Removes a task from the list.
     *
     * @param index Index of the task.
     * @return The removed task.
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size()
                : "Task index out of bounds";
        return tasks.remove(index);
    }

    /**
     * Retrieves a task by index.
     *
     * @param index Index of the task.
     * @return The requested task.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size()
                : "Task index out of bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getAll() {
        return tasks;
    }

    /**
     * Prints all tasks to the console.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
