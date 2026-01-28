package boffbot;

import java.io.IOException;

/**
 * The main entry point of the BoffBot application.
 * BoffBot handles user interaction, command parsing, and task management.
 */
public class BoffBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a BoffBot instance with a given file path for data storage.
     *
     * @param filePath The file path used to load and save tasks.
     */
    public BoffBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | BoffBotException e) {
            ui.showMessage("Error loading data file: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop of the chatbot until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                isExit = Parser.parse(input, tasks, ui, storage);

            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new BoffBot("data/boffbot.txt").run();
    }
}
