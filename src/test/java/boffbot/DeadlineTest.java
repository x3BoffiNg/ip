package boffbot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void constructor_validDate_success() throws BoffBotException {
        Deadline d = new Deadline("submit report", "2024-12-01");
        String result = d.toString();

        assertTrue(result.contains("submit report"));
        assertTrue(result.contains("Dec"));
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        assertThrows(BoffBotException.class, () -> {
            new Deadline("submit report", "12/01/2024");
        });
    }
}
