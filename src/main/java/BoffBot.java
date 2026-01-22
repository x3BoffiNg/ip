import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoffBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<>();

        String logo = "------------------------\n"
                + "Hello! I'm BoffBot\n"
                + "What can I do for you?\n"
                + "------------------------\n";
        System.out.println(logo);

        while(true){

            System.out.println("Input: ");
            String input = sc.nextLine();

            if(input.equalsIgnoreCase("bye")){
                System.out.println("------------------------");
                System.out.println("          " + input);
                break;

            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("------------------------");
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            }else if(input.startsWith("mark ") || input.startsWith("unmark ")){
                String[] part = input.split(" ");

                try {
                    //Validate command format
                    if (part.length < 2) {
                        throw new BoffBotException("Invalid input!! Please Use: mark <number> or unmark <number>");
                    }

                    int taskNum = Integer.parseInt(part[1]);

                    //Validate task number range
                    if (taskNum < 1 || taskNum > taskList.size()) {
                        throw new BoffBotException("Invalid!! Please enter a number between 1 and " + taskList.size());
                    }
                    Task task = taskList.get(taskNum - 1);

                    if (part[0].equalsIgnoreCase("mark")) {
                        task.mark();
                        System.out.println("------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);

                    } else if (part[0].equalsIgnoreCase("unmark")) {
                        task.unmark();
                        System.out.println("------------------------");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task);

                    }

                } catch (BoffBotException e) {
                    System.out.println(e.getMessage());
                }
            }else if (input.startsWith("todo")) {
                try {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new BoffBotException("Invalid input!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(description);
                    taskList.add(newTask);
                    System.out.println("------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }catch(BoffBotException e){
                    System.out.println("------------------------");
                    System.out.println(e.getMessage());
                }
            }else if (input.startsWith("deadline")) {
                try {
                    String res = input.substring(8).trim();
                    String[] parts = res.split(" /by ");

                    if (parts.length < 2) {
                        throw new BoffBotException("Invalid!!! Please format as such: deadline <description> /by <date>");
                    } else {
                        String description = parts[0].trim();
                        String duedate = parts[1].trim();

                        Task newTask = new Deadline(description, duedate);
                        taskList.add(newTask);
                        System.out.println("------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    }
                } catch (BoffBotException e) {
                    System.out.println("------------------------");
                    System.out.println(e.getMessage());
                }
            }else if (input.startsWith("event")) {
                try {
                    String rest = input.substring(5).trim();
                    String[] parts = rest.split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new BoffBotException("Invalid input!! Please format it as such: <description> /from <start> /to <end>");

                    } else {
                        String description = parts[0].trim();
                        String start = parts[1].trim();
                        String end = parts[2].trim();
                        Task newTask = new Event(description, start, end);
                        taskList.add(newTask);
                        System.out.println("------------------------");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

                    }
                }catch (BoffBotException e) {
                    System.out.println("------------------------");
                    System.out.println(e.getMessage());
                }

            }
            else{
                try {
                    throw new BoffBotException("I'm Sorry I don't know what that means TT");

                }catch(BoffBotException e){
                    System.out.println("------------------------");
                    System.out.println(e.getMessage());
                }
            }
        }

        String logo2 = "------------------------\n"
                + "Thanks for using BoffBot!!\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------\n";
        System.out.println(logo2);

    }
}
