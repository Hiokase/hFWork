package hokase.hfwork.api;

import hokase.hfwork.inventorys.guis.InventoryGUI;
import hokase.hfwork.inventorys.guis.PaginatedGUI;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;

public class InventoryAPI {
    public static InventoryGUI createInventory(String title, int size, List<ItemStack> items) {
        return new InventoryGUI(title, size, items);
    }

    public static PaginatedGUI createPaginatedInventory(String title, int size, Function<Integer, List<ItemStack>> itemProvider) {
        return new PaginatedGUI(title, size, itemProvider);
    }
}