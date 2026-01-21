import java.util.Scanner;

public class BoffBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = "------------------------\n"
                + "Hello! I'm BoffBot\n"
                + "What can I do for you?\n"
                + "------------------------\n";
        System.out.println(logo);

        while(true){

            System.out.println("Input: ");
            String input = sc.nextLine();

            if(input.equalsIgnoreCase("bye")){
                System.out.println("------------------------\n");
                System.out.println("       " + input);

                break;
            }

            System.out.println("------------------------\n");
            System.out.println("       " +input);
            System.out.println("------------------------\n");

        }

        String logo2 = "------------------------\n"
                + "Thanks for using BoffBot!!\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------\n";
        System.out.println(logo2);

    }
}
