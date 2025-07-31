package utilites;

import java.util.Scanner;

public class Helper {
    private static final Scanner inp = new Scanner(System.in);
    public static int getChoice(int num) {
        int choice;
        do {
            try {
                choice = Integer.parseInt(inp.nextLine());
            }catch (Exception e){
                choice = 0;
            }
            if (choice < 1 || choice > num) System.out.println("Invalid choice. Please try again.");

        } while (choice < 1 || choice > num);
        return choice;
    }
}
