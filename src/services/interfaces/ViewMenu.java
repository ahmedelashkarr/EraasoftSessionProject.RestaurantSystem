package services.interfaces;

import data.DishData;
import model.Dish;

public interface ViewMenu {
    default void viewMenu() {
        if (DishData.dishesList.isEmpty()) {
            System.out.println("** ⚠️ No menu added yet. **");
            return;
        }
        System.out.println("\n\n** \uD83D\uDCCB THE MENU **");
        for (int i = 0; i < DishData.dishesList.size(); i++) {
            Dish dish = DishData.dishesList.get(i);
            System.out.printf("%d) %s %.1f$\n", i + 1, dish.getName(), dish.getPrice());
        }

    }
}
