package boffbot;

import java.io.*;
import java.util.*;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return A list of loaded tasks.
     * @throws IOException If file access fails.
     * @throws BoffBotException If task data is invalid.
     */
    public List<Task> load() throws IOException, BoffBotException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }

        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            tasks.add(Task.fromFileFormat(s.nextLine()));
        }
        s.close();
        return tasks;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If file writing fails.
     */
    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
