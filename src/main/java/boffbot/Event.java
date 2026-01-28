package boffbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate  start;
    protected LocalDate  end;

    private static final DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs an Event task.
     *
     * @param description The event description.
     * @param start The start date in yyyy-MM-dd format.
     * @param end The end date in yyyy-MM-dd format.
     * @throws BoffBotException If date format is invalid.
     */
    public Event(String description, String start, String end) throws BoffBotException {
        super(description);

        try {
            this.start = LocalDate.parse(start, input);
            this.end = LocalDate.parse(end, input);

        } catch (DateTimeParseException e) {
            throw new BoffBotException("Date must be in format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (start: " + start.format(output) + " end: " + end.format(output) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

}