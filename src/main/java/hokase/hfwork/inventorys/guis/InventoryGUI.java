package hokase.hfwork.inventorys.guis;

import hokase.hfwork.listeners.InventoryListener;
import hokase.hfwork.models.SlotAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryGUI {
    private final Inventory inventory;
    private final Map<Integer, SlotAction> actions = new HashMap<>();

    public InventoryGUI(String title, int size, List<ItemStack> items) {
        this.inventory = Bukkit.createInventory(null, size, title);
        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }
    }

    public void setAction(int slot, SlotAction action) {
        actions.put(slot, action);
    }

    public void open(Player player) {
        InventoryListener.registerInventory(this);
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Map<Integer, SlotAction> getActions() {
        return actions;
    }
}
