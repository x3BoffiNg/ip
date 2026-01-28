package boffbot;

/**
 * Represents a simple todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with a description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo task.
     *
     * @return Formatted Todo string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}