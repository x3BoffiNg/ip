import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoffBot {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> inputList = new ArrayList<>();

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
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("------------------------\n");
                for (int i=0; i < inputList.size(); i++ ){
                    System.out.println((i+1) +". " + inputList.get(i));
                }

            }
            else{
                inputList.add(input);
                System.out.println("------------------------\n");
                System.out.println("added: " +input);
                System.out.println("------------------------\n");
            }


        }

        String logo2 = "------------------------\n"
                + "Thanks for using BoffBot!!\n"
                + "Bye. Hope to see you again soon!\n"
                + "------------------------\n";
        System.out.println(logo2);

    }
}
