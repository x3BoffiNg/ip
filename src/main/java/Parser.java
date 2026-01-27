public class Parser {

    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {

        // EXIT
        if (input.equalsIgnoreCase("bye")) {
            ui.showMessage("Bye. Hope to see you again soon!");
            return true;
        }

        // LIST
        if (input.equalsIgnoreCase("list")) {
            ui.showTaskList(tasks);
            return false;
        }

        // MARK / UNMARK
        if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            String[] part = input.split(" ");

            if (part.length < 2) {
                throw new BoffBotException("Invalid input!! Please Use: mark <number> or unmark <number>");
            }

            int taskNum;
            try {
                taskNum = Integer.parseInt(part[1]);
            } catch (NumberFormatException e) {
                throw new BoffBotException("Please enter a valid number after '" + part[0] + "'.");
            }

            if (taskNum < 1 || taskNum > tasks.size()) {
                throw new BoffBotException("Invalid!! Please enter a number between 1 and " + tasks.size());
            }

            Task task = tasks.get(taskNum - 1);

            if (part[0].equalsIgnoreCase("mark")) {
                task.mark();
                ui.showMessage("Nice! I've marked this task as done:");
            } else {
                task.unmark();
                ui.showMessage("OK, I've marked this task as not done yet:");
            }

            ui.showMessage(task.toString());
            storage.save(tasks.getAll());
            return false;
        }

        // TODO
        if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new BoffBotException("Invalid input!!! The description of a todo cannot be empty.");
            }

            Task newTask = new Todo(description);
            tasks.add(newTask);
            storage.save(tasks.getAll());

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(newTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        // DEADLINE
        if (input.startsWith("deadline")) {
            String res = input.substring(8).trim();
            String[] parts = res.split(" /by ");

            if (parts.length < 2) {
                throw new BoffBotException("Invalid!!! Please format as such: deadline <description> /by <yyyy-mm-dd>");
            }

            String description = parts[0].trim();
            String duedate = parts[1].trim();

            Task newTask = new Deadline(description, duedate);
            tasks.add(newTask);
            storage.save(tasks.getAll());

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(newTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        // EVENT
        if (input.startsWith("event")) {
            String rest = input.substring(5).trim();
            String[] parts = rest.split(" /from | /to ");

            if (parts.length < 3) {
                throw new BoffBotException("Invalid input!! Please format it as such: <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
            }

            String description = parts[0].trim();
            String start = parts[1].trim();
            String end = parts[2].trim();

            Task newTask = new Event(description, start, end);
            tasks.add(newTask);
            storage.save(tasks.getAll());

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(newTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        // DELETE
        if (input.startsWith("delete")) {
            String[] part = input.split(" ");

            if (part.length < 2) {
                throw new BoffBotException("Invalid input!! Please Use: delete <number>");
            }

            int taskNum;
            try {
                taskNum = Integer.parseInt(part[1]);
            } catch (NumberFormatException e) {
                throw new BoffBotException("Please enter a valid number after '" + part[0] + "'.");
            }

            if (taskNum < 1 || taskNum > tasks.size()) {
                throw new BoffBotException("Invalid!! Please enter a number between 1 and " + tasks.size());
            }

            Task removedTask = tasks.remove(taskNum - 1);
            storage.save(tasks.getAll());

            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(removedTask.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        // UNKNOWN COMMAND
        throw new BoffBotException("I'm Sorry I don't know what that means TT");
    }
}
