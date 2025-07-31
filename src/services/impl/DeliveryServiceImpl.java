package services.impl;

import enums.OrderStatus;
import model.Chef;
import model.DeliveryGuy;
import model.Order;
import model.User;
import services.interfaces.DeliveryService;
import services.interfaces.UserService;
import utilites.Helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static data.OrderData.orderList;

public class DeliveryServiceImpl implements DeliveryService, UserService {
    public static final Scanner inp = new Scanner(System.in);

    /// Singleton design pattern
    private static class Holder {
        private static final DeliveryServiceImpl INSTANCE = new DeliveryServiceImpl();
    }

    public static DeliveryServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void login(User user) {
        DeliveryGuy currentDeliveryGuy = (DeliveryGuy) user;

        int choice;
        do {

            System.out.printf("** Welcome DeliveryGuy %s \uD83D\uDE9A **\n", currentDeliveryGuy.getUsername());
            System.out.println("1. View Orders");
            System.out.println("2. Update the status");
            System.out.println("3. Exit");
            System.out.print("\uD83D\uDCDD Please select an option: ");

            choice = Helper.getChoice(3);
            switch (choice) {
                case 1 -> {
                    viewOrders();
                    System.out.print("\uD83D\uDCDD Enter Any key to exit: ");
                    inp.nextLine();
                }
                case 2 -> {
                    viewOrders();
                    updateOrderStatus();
                }
            }

        } while (choice != 3);
        System.out.println("\n\n\n\n");
    }
}
