package boffbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate dueDate;

    private static final DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String dueDate) throws BoffBotException {
        super(description);

        try {
            this.dueDate = LocalDate.parse(dueDate, input);
        } catch (DateTimeParseException e){
            throw new BoffBotException("Date must be in format yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(output) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }

}