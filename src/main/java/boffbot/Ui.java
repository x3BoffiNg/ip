package boffbot;

import java.util.Scanner;

/**
 * Handles all user interface interactions such as displaying messages
 * and reading user input from the console.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("------------------------");
        System.out.println("Hello! I'm boffbot.BoffBot");
        System.out.println("What can I do for you?");
        System.out.println("------------------------");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        System.out.println("Input: ");
        return sc.nextLine();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println("------------------------");
    }

    /**
     * Displays a message to the user.
     *
     * @param msg The message to be displayed.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        tasks.printTasks();
    }
}
