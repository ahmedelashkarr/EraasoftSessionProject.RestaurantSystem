
import data.UserData;
import factories.ServiceFactory;
import model.User;
import services.impl.CustomerServiceImpl;
import services.interfaces.UserService;
import utilites.Helper;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Program {
    private static final Scanner inp = new Scanner(System.in);
    private static final List<User> userList = UserData.userList;
    private static Optional<User> user;
    private static final CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();

    public static void run() {
        while (true) {
            showMenu();
            System.out.print("Please select an option: ");
            int choice = Helper.getChoice(3);
            switch (choice) {
                case 1 -> customerService.register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
            }
        }
    }

    private static void login() {
        while (true) {
            System.out.println("\n-------- Login --------");
            System.out.println("* Enter 0 to exit *");

            System.out.print("\uD83D\uDCDD Enter username: ");
            String username = inp.nextLine().trim();
            if (username.equals("0")) {
                System.out.println("\n\n\n\n");
                return;
            }

            user = userList.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst();
            if (user.isEmpty()) {
                System.out.print("\n**** ⚠️ Username doesn't exist! ****");
                continue;
            }
            System.out.print("\uD83D\uDCDD Enter password: ");
            String password = inp.nextLine();

            if (!user.get().getPassword().equals(password)) {
                System.out.println("\n**** ❌ Incorrect password! ****");
                continue;
            }
            System.out.println("\n\n===== ✅ Login successful!  =====");

            UserService userService = ServiceFactory.createService(user.get().getClass().getSimpleName());
            if (userService == null) {
                System.out.println(" ⚠️ Service not found for user type.");
                return;
            }
            userService.login(user.get());
            return ;
        }
    }

    private static void showMenu() {
        System.out.println("===== Welcome to our restaurant \uD83C\uDF77 =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }


}
