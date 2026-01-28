package boffbot;

/**
 * Represents custom exceptions specific to the BoffBot application.
 */
public class BoffBotException extends Exception {
    /**
     * Constructs a BoffBotException with an error message.
     *
     * @param message The error message.
     */
    public BoffBotException(String message) {
        super(message);
    }
}