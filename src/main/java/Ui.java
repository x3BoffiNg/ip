import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("------------------------");
        System.out.println("Hello! I'm BoffBot");
        System.out.println("What can I do for you?");
        System.out.println("------------------------");
    }

    public String readCommand() {
        System.out.println("Input: ");
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("------------------------");
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        tasks.printTasks();
    }
}
