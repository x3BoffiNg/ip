import java.io.IOException;
public class BoffBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
