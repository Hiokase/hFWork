package hokase.hfwork.inventorys.guis;

import hokase.hfwork.listeners.InventoryListener;
import hokase.hfwork.models.SlotAction;
import hokase.hfwork.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PaginatedGUI {
    private final String title;
    private final int size;
    private final Function<Integer, List<ItemStack>> itemProvider;
    private int currentPage = 0;
    private final Map<Integer, SlotAction> actions = new HashMap<>();

    public PaginatedGUI(String title, int size, Function<Integer, List<ItemStack>> itemProvider) {
        this.title = title;
        this.size = size;
        this.itemProvider = itemProvider;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, size, title + " - Page " + (currentPage + 1));
        List<ItemStack> items = itemProvider.apply(currentPage);
        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }

        if (currentPage > 0) {
            inventory.setItem(size - 9, new ItemBuilder(Material.ARROW).setDisplayName("Previous Page").build());
        }
        if (!itemProvider.apply(currentPage + 1).isEmpty()) {
            inventory.setItem(size - 1, new ItemBuilder(Material.ARROW).setDisplayName("Next Page").build());
        }

        InventoryListener.registerInventory(this);
        player.openInventory(inventory);
    }

    public void nextPage() {
        currentPage++;
    }

    public void previousPage() {
        currentPage--;
    }

    public Inventory getCurrentPage() {
        Inventory inventory = Bukkit.createInventory(null, size, title + " - Page " + (currentPage + 1));
        List<ItemStack> items = itemProvider.apply(currentPage);
        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }

        if (currentPage > 0) {
            inventory.setItem(size - 9, new ItemBuilder(Material.ARROW).setDisplayName("Previous Page").build());
        }
        if (!itemProvider.apply(currentPage + 1).isEmpty()) {
            inventory.setItem(size - 1, new ItemBuilder(Material.ARROW).setDisplayName("Next Page").build());
        }

        return inventory;
    }

    public Map<Integer, SlotAction> getActions() {
        return actions;
    }
}
