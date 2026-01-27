import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate  start;
    protected LocalDate  end;

    private static final DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy");

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
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }

}