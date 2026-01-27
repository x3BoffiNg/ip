import java.io.*;
import java.util.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();

        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            tasks.add(Task.fromFileFormat(s.nextLine()));
        }
        s.close();
        return tasks;
    }

    public void save(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            fw.write(t.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
