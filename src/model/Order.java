package model;

public class Order {
    private static int count = 0;
    private int id;
    private Dish dish;
    private int quantity;
    private Customer customer;
    private String orderStatus;

    public Order(Dish dish, int quantity, Customer customer, String orderStatus) {
        count++;
        id = count;
        this.dish = dish;
        this.quantity = quantity;
        this.customer = customer;
        this.orderStatus = orderStatus;
    }

    public Order(Dish dish, int quantity, String orderStatus) {
        this.dish = dish;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }
    public int getId() {
        return id;
    }
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


}
