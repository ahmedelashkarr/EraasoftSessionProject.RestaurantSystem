package services.impl;

import data.DishData;
import data.OrderData;
import data.UserData;
import enums.OrderStatus;
import model.Customer;
import model.Dish;
import model.Order;
import model.User;
import services.interfaces.CustomerService;
import services.interfaces.Registerable;
import services.interfaces.UserService;
import services.interfaces.ViewMenu;
import utilites.Helper;

import java.util.List;
import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService, Registerable, UserService, ViewMenu {
    private static Customer currentCustomer;
    private static final List<User> userList = UserData.userList;
    public static final List<Dish> dishList = DishData.dishesList;
    public static final Scanner inp = new Scanner(System.in);

    /// Singleton design pattern
    private static class Holder {
        private static final CustomerServiceImpl INSTANCE = new CustomerServiceImpl();
    }

    public static CustomerServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    ///
    @Override
    public boolean register() {
        while (true) {
            System.out.println("\n-------- Register --------");
            System.out.println("* Enter 0 to exit *");

            System.out.print("\uD83D\uDCDD Enter username: ");
            String username = inp.nextLine().trim();
            if (username.equals("0")) {
                System.out.println("\n\n\n\n");
                return false;
            }

            boolean exists = userList.stream()
                    .map(User::getUsername)
                    .anyMatch(user -> user.equals(username));
            if (exists) {
                System.out.print("\n**** ⚠️ Username already exists! Try a different one. ****");
                continue;
            }
            System.out.print("\uD83D\uDCDD Enter password: ");
            String password = inp.nextLine();

            Customer newUser = new Customer(username, password);
            userList.add(newUser);
            UserData.userList.add(newUser);

            System.out.print("\n\n===== ✅ Registration successful!  =====\n");
            return true;
        }
    }

    @Override
    public void login(User user) {
        currentCustomer = (Customer) user;

        int choice;
        do {

            System.out.printf("** \uD83D\uDE4D Welcome %s **\n", currentCustomer.getUsername());
            System.out.println("1. View menu");
            System.out.println("2. View your orders");
            System.out.println("3. Exit");
            System.out.print("\uD83D\uDCDD Please select an option: ");

            choice = Helper.getChoice(3);
            switch (choice) {
                case 1 -> {
                    viewMenu();
                    askForOrder();
                }
                case 2 -> viewCustomerOrders();
            }

        } while (choice != 3);
        System.out.println("\n\n\n\n");
    }


    private void askForOrder() {
        System.out.println("-------------------------");
        System.out.println("1. Make Order");
        System.out.println("2. Exit");
        System.out.print("\uD83D\uDCDD Please select an option: ");
        int choice = Helper.getChoice(2);
        if (choice == 1) makeOrder();
        else if (choice == 2) System.out.println("\n\n\n\n");
    }

    @Override
    public void makeOrder() {
        boolean ordering = true;
        while (ordering) {
            viewMenu();
            System.out.print("  \uD83D\uDCDD Select one to add to your order : ");
            int choice = Helper.getChoice(dishList.size());

            System.out.print("** ⚠️ Max quantity is 10 **\n  \uD83D\uDCDDEnter quantity: ");
            int quantity = Helper.getChoice(10);
            saveOrder(dishList.get(choice - 1), quantity);

            System.out.println("\n1. Add another item");
            System.out.println("2. Finish order");
            System.out.print(" \uD83D\uDCDD Please select an option: ");
            choice = Helper.getChoice(2);
            if (choice == 2) {
                ordering = false;
                System.out.println("\n\n\n\n");
            }
        }
    }

    public void saveOrder(Dish dish, int quantity) {
        Order order = new Order(dish, quantity, currentCustomer, OrderStatus.PENDING.name());
        currentCustomer.getOrders().add(order);
        OrderData.orderList.add(order);
        System.out.println("\n\n\n\n");
        System.out.print("** ✅ Order added Successfully **");
    }

    public void viewCustomerOrders() {
        if (currentCustomer.getOrders().isEmpty()) System.out.println("\n\n*** \uD83D\uDC40 You didn't make any order yet !! ***");
        else {
            System.out.println("\n\n** \uD83D\uDCE6 Your Order **");
            List<Order> customerOrders = currentCustomer.getOrders();
            for (int i = 0; i < customerOrders.size(); i++) {
                Order order = customerOrders.get(i);
                System.out.printf("%d- %dx%s %.1f$ (%s)\n", i + 1, order.getQuantity(), order.getDish().getName(), order.getDish().getPrice() * order.getQuantity(), order.getOrderStatus());
            }
            System.out.print(" \uD83D\uDCDD Enter Any key to exit: ");
            inp.nextLine();
            System.out.println("\n\n\n\n");
        }
    }


}