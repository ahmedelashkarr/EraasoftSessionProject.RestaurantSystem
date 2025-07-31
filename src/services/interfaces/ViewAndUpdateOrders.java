package services.interfaces;

import data.OrderData;
import enums.OrderStatus;
import model.Order;
import utilites.Helper;

import java.util.*;
import java.util.stream.Collectors;

import static data.OrderData.orderList;

public interface ViewAndUpdateOrders {
    default void viewOrders() {
        if (OrderData.orderList.isEmpty()) {
            System.out.println("*** ⚠️ No order added yet. ***");
            return;
        }
        System.out.println("\n\n** THE Orders \uD83C\uDF7D\uFE0F **");
        Map<String, List<Order>> ordersByCustomer = OrderData.orderList.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .collect(Collectors.groupingBy(order -> order.getCustomer().getUsername()));

        for (Map.Entry<String, List<Order>> entry : ordersByCustomer.entrySet()) {
            String customerName = entry.getKey();
            List<Order> customerOrders = entry.getValue();

            System.out.println("-Customer: " + customerName);
            for (Order order : customerOrders) {

                System.out.printf("\t%d) %dx%s \"%s\"\n", order.getId(), order.getQuantity(),
                        order.getDish().getName(),
                        order.getOrderStatus());
            }
        }
    }

    default void updateOrderStatus() {
        System.out.print("\uD83D\uDCDD Choose order to change the Status: ");
        int choice = Helper.getChoice(orderList.size());
        List<OrderStatus> orderStatusList = Arrays.stream(OrderStatus.values()).toList();
        for (int i = 0; i < orderStatusList.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, orderStatusList.get(i).toString());
        }
        System.out.print("\uD83D\uDCDD Choose Status: ");
        int status = Helper.getChoice(orderStatusList.size());
        String orderStatus = Arrays.stream(OrderStatus.values()).toList().get(status - 1).toString();
        Optional<Order> order = orderList.stream()
                .filter(order1 -> order1.getId() == choice)
                .findFirst();

        if (order.isEmpty()) {
            System.out.println("** ⚠️ We can't get your order number!! **");
            return;
        }
        order.get().setOrderStatus(orderStatus);
        System.out.println("\n\n\n\n** ✅ Order Edit successfully **");
    }
}


