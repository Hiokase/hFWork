package hokase.hfwork.bukkit.inventorys;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    public static void fillInventory(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    public static void addItem(Inventory inventory, ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    public static void addItem(Inventory inventory, ItemStack item) {
        inventory.addItem(item);
    }
}
