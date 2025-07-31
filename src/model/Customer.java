package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    List<Order> orders = new ArrayList<>();

    public Customer(String username , String password) {
        super(username , password);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
