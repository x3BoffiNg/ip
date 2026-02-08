package boffbot;

/**
 * Parses user commands and executes corresponding actions.
 */
public class Parser {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    /**
     * Parses user input and performs the corresponding command.
     *
     * @param input The user input.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return True if the user wants to exit, false otherwise.
     * @throws Exception If the command is invalid.
     */
    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {

        if (isExitCommand(input, ui)) {
            return true;
        }

        if (isListCommand(input, tasks, ui)) {
            return false;
        }

        if (handleMarkUnmark(input, tasks, ui, storage)) {
            return false;
        }

        if (handleTodo(input, tasks, ui, storage)) {
            return false;
        }

        if (handleDeadline(input, tasks, ui, storage)) {
            return false;
        }

        if (handleEvent(input, tasks, ui, storage)) {
            return false;
        }

        if (handleDelete(input, tasks, ui, storage)) {
            return false;
        }

        if (handleFind(input, tasks, ui)) {
            return false;
        }

        throw new BoffBotException("I'm Sorry I don't know what that means TT");
    }

    /**
     * Checks if the input is the exit command and handles program termination.
     *
     * @param input User input string.
     * @param ui    User interface for displaying messages.
     * @return {@code true} if exit command was handled, {@code false} otherwise.
     */
    private static boolean isExitCommand(String input, Ui ui) {
        if (input.equalsIgnoreCase(COMMAND_BYE)) {
            ui.showMessage("Bye. Hope to see you again soon!");
            return true;
        }
        return false;
    }

    /**
     * Checks if the input is the list command and displays all tasks.
     *
     * @param input User input string.
     * @param tasks Task list containing all tasks.
     * @param ui    User interface for displaying messages.
     * @return {@code true} if list command was handled, {@code false} otherwise.
     */
    private static boolean isListCommand(String input, TaskList tasks, Ui ui) {
        if (input.equalsIgnoreCase(COMMAND_LIST)) {
            ui.showTaskList(tasks);
            return true;
        }
        return false;
    }

    /**
     * Handles marking or unmarking a task as completed.
     *
     * @param input   User input string.
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @return {@code true} if mark/unmark command was handled, {@code false} otherwise.
     * @throws Exception If task index is invalid.
     */
    private static boolean handleMarkUnmark(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.startsWith(COMMAND_MARK + " ") && !input.startsWith(COMMAND_UNMARK + " ")) {
            return false;
        }

        String[] parts = input.split(" ");
        int taskNum = parseTaskNumber(parts, parts[0], tasks.size());
        Task task = tasks.get(taskNum - 1);

        if (parts[0].equalsIgnoreCase(COMMAND_MARK)) {
            task.mark();
            ui.showMessage("Nice! I've marked this task as done:");
        } else {
            task.unmark();
            ui.showMessage("OK, I've marked this task as not done yet:");
        }

        ui.showMessage(task.toString());
        storage.save(tasks.getAll());
        return true;
    }

    /**
     * Handles adding a todo task.
     *
     * @param input   User input string.
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @return {@code true} if todo command was handled, {@code false} otherwise.
     * @throws Exception If description is empty.
     */
    private static boolean handleTodo(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.startsWith(COMMAND_TODO)) {
            return false;
        }

        String description = input.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new BoffBotException("Invalid input!!! The description of a todo cannot be empty.");
        }

        Task task = new Todo(description);
        addTask(tasks, ui, storage, task);
        return true;
    }

    /**
     * Handles adding a deadline task.
     *
     * @param input   User input string.
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @return {@code true} if deadline command was handled, {@code false} otherwise.
     * @throws Exception If input format is invalid.
     */
    private static boolean handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.startsWith(COMMAND_DEADLINE)) {
            return false;
        }

        String arguments = input.substring(COMMAND_DEADLINE.length()).trim();
        String[] parts = arguments.split(" /by ");

        if (parts.length < 2) {
            throw new BoffBotException(
                    "Invalid!!! Please format as such: deadline <description> /by <yyyy-mm-dd>");
        }

        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        addTask(tasks, ui, storage, task);
        return true;
    }

    /**
     * Handles adding an event task.
     *
     * @param input   User input string.
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @return {@code true} if event command was handled, {@code false} otherwise.
     * @throws Exception If input format is invalid.
     */
    private static boolean handleEvent(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.startsWith(COMMAND_EVENT)) {
            return false;
        }

        String arguments = input.substring(COMMAND_EVENT.length()).trim();
        String[] parts = arguments.split(" /from | /to ");

        if (parts.length < 3) {
            throw new BoffBotException(
                    "Invalid input!! Please format it as such: <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        }

        Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        addTask(tasks, ui, storage, task);
        return true;
    }

    /**
     * Handles deleting a task.
     *
     * @param input   User input string.
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @return {@code true} if delete command was handled, {@code false} otherwise.
     * @throws Exception If task index is invalid.
     */
    private static boolean handleDelete(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!input.startsWith(COMMAND_DELETE)) {
            return false;
        }

        String[] parts = input.split(" ");
        int taskNum = parseTaskNumber(parts, COMMAND_DELETE, tasks.size());
        Task removed = tasks.remove(taskNum - 1);

        storage.save(tasks.getAll());
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Handles searching for tasks containing a given keyword.
     *
     * @param input User input string.
     * @param tasks Task list containing all tasks.
     * @param ui    User interface for displaying messages.
     * @return {@code true} if find command was handled, {@code false} otherwise.
     */
    private static boolean handleFind(String input, TaskList tasks, Ui ui) throws BoffBotException {
        if (!input.startsWith(COMMAND_FIND)) {
            return false;
        }

        String keyword = input.substring(COMMAND_FIND.length()).trim();
        if (keyword.isEmpty()) {
            throw new BoffBotException("Please provide a keyword to search for.");
        }

        ui.showMessage("Here are the matching tasks in your list:");
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                ui.showMessage((count + 1) + ". " + task);
                count++;
            }
        }

        if (count == 0) {
            ui.showMessage("No matching tasks found.");
        }

        return true;
    }

    /**
     * Parses and validates a task number from user input.
     *
     * @param parts   Tokenized user input.
     * @param command Command name used in error messages.
     * @param max     Maximum valid task index.
     * @return Parsed task number.
     * @throws BoffBotException If task number is invalid.
     */
    private static int parseTaskNumber(String[] parts, String command, int max) throws BoffBotException {
        if (parts.length < 2) {
            throw new BoffBotException("Invalid input!! Please Use: " + command + " <number>");
        }

        int taskNum;
        try {
            taskNum = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new BoffBotException("Please enter a valid number after '" + command + "'.");
        }

        if (taskNum < 1 || taskNum > max) {
            throw new BoffBotException("Invalid!! Please enter a number between 1 and " + max);
        }

        return taskNum;
    }

    /**
     * Adds a task to the task list and displays confirmation messages.
     *
     * @param tasks   Task list containing all tasks.
     * @param ui      User interface for displaying messages.
     * @param storage Storage handler for saving tasks.
     * @param task    Task to be added.
     * @throws Exception If saving fails.
     */
    private static void addTask(TaskList tasks, Ui ui, Storage storage, Task task) throws Exception {
        tasks.add(task);
        storage.save(tasks.getAll());

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
