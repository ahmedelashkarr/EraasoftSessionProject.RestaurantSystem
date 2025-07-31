package factories;

import services.impl.ChefServiceImpl;
import services.impl.CustomerServiceImpl;
import services.impl.DeliveryServiceImpl;
import services.interfaces.CustomerService;
import services.interfaces.UserService;

public class ServiceFactory {
    public static UserService createService(String type) {
        if (type.equalsIgnoreCase("Customer"))
            return new CustomerServiceImpl();
        else if (type.equalsIgnoreCase("Chef"))
            return new ChefServiceImpl();
        else if (type.equalsIgnoreCase("DeliveryGuy"))
            return new DeliveryServiceImpl();
        else
            return null;

    }
}
