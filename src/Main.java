import data.DishData;
import data.OrderData;
import data.UserData;
import model.*;

public class Main {
    public static void main(String[] args) {
        Dish Chicken = new Dish("Chicken", 200);
        Dish Steak = new Dish("Steak", 250);
        Dish Burger = new Dish("Burger", 150);
        Dish Pizza = new Dish("Pizza", 180);

        DishData.dishesList.add(Chicken);
        DishData.dishesList.add(Steak);
        DishData.dishesList.add(Burger);
        DishData.dishesList.add(Pizza);

        Customer customer1 = new Customer("cust", "1");
        Customer customer2 = new Customer("cust2", "1");
        Chef chef = new Chef("chef", "1");
        DeliveryGuy deliveryGuy = new DeliveryGuy("guy", "1");

        UserData.userList.add(customer1);
        UserData.userList.add(customer2);
        UserData.userList.add(chef);
        UserData.userList.add(deliveryGuy);

        OrderData.orderList.add(new Order(Chicken, 2, customer1, "Pending"));
        OrderData.orderList.add(new Order(Steak, 2, customer1, "Pending"));
        OrderData.orderList.add(new Order(Burger, 2, customer2, "Pending"));
        OrderData.orderList.add(new Order(Burger, 2, customer2, "Pending"));
        Program.run();

    }
}
