package services.impl;

import data.DishData;
import enums.OrderStatus;
import model.Chef;
import model.Dish;
import model.Order;
import model.User;
import services.interfaces.ChefService;
import services.interfaces.UserService;
import utilites.Helper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static data.OrderData.orderList;

public class ChefServiceImpl implements ChefService, UserService {
    public static final List<Dish> dishList = DishData.dishesList;
    public static final Scanner inp = new Scanner(System.in);


    /// Singleton design pattern
    private static class Holder {
        private static final ChefServiceImpl INSTANCE = new ChefServiceImpl();
    }

    public static ChefServiceImpl getInstance() {
        return ChefServiceImpl.Holder.INSTANCE;
    }

    @Override
    public void login(User user) {
        Chef currentChef = (Chef) user;

        int choice;
        do {

            System.out.printf("** Welcome Chef %s \uD83D\uDC68\u200D\uD83C\uDF73 **\n", currentChef.getUsername());
            System.out.println("1. Add Dish");
            System.out.println("2. Edit Dish");
            System.out.println("3. Delete Dish");
            System.out.println("4. View Menu");
            System.out.println("5. View Orders and Update the status");
            System.out.println("6. Exit");
            System.out.print("\uD83D\uDCDD Please select an option: ");

            choice = Helper.getChoice(6);
            switch (choice) {
                case 1 -> addDish();
                case 2 -> editDish();
                case 3 -> deleteDish();
                case 4 -> {
                    viewMenu();
                    System.out.print("\uD83D\uDCDDEnter Any key to exit: ");
                    inp.nextLine();

                }
                case 5 -> viewOrders();
            }

        } while (choice != 6);
        System.out.println("\n\n\n\n");
    }

    @Override
    public void addDish() {
        viewMenu();
        System.out.println("\n- Enter details for the new dish:");
        String dishName = validationDishName();

        double price = validationDishPrice();

        Dish dish = new Dish(dishName, price);
        dishList.add(dish);
        System.out.printf("** Dish \"%s\" added successfully at %.1f$ **", dishName, price);
    }

    @Override
    public void editDish() {
        viewMenu();
        System.out.print("\uD83D\uDCDD Choose one to edit: ");
        int choice = Helper.getChoice(dishList.size());
        String newName = validationDishName();
        double price = validationDishPrice();
        dishList.get(choice - 1).setName(newName);
        dishList.get(choice - 1).setPrice(price);
        System.out.printf("** ✅ Dish \"%s\" Edit successfully at %.1f$ **\n", newName, price);

    }

    public void deleteDish() {
        viewMenu();
        System.out.print("Choose one to DELETE: ");
        int choice = Helper.getChoice(dishList.size());
        dishList.remove(choice - 1);
        System.out.print("**✅ Dish Deleted successfully **");

    }


    @Override
    public void viewOrders() {
        ChefService.super.viewOrders();
        System.out.println("1) Update the status");
        System.out.println("2) Exit");
        System.out.print("\uD83D\uDCDD Enter Choice: ");
        int choice = Helper.getChoice(2);
        if (choice == 1) {
            System.out.println("-----------------------------");
            updateOrderStatus();
        }
    }

    private String validationDishName() {
        String newName;
        while (true) {
            System.out.print("\uD83D\uDCDD Dish name: ");
            newName = inp.nextLine().trim();

            if (newName.isEmpty()) {
                System.out.println("** ⚠️ Dish name cannot be empty. Please try again. **");
                continue;
            }

            String finalDishName = newName;
            boolean exists = dishList.stream()
                    .anyMatch(d -> d.getName().equalsIgnoreCase(finalDishName));

            if (exists) {
                System.out.println("** ⚠️ Dish with this name already exists. Please enter a different name. **");
            } else {
                break;
            }
        }
        return newName;
    }

    private double validationDishPrice() {
        double price;
        while (true) {
            System.out.print("\uD83D\uDCDD Dish price: ");
            try {
                price = Double.parseDouble(inp.nextLine());
                if (price <= 0) {
                    System.out.println("** ⚠️ Price must be greater than 0 !! **");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("** ⚠️ Invalid number. Please enter a valid price !! **");
            }
        }
        return price;
    }
}
