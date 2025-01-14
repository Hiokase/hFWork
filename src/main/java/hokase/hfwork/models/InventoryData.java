package hokase.hfwork.models;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryData {
    private final Map<Integer, ItemStack> items = new HashMap<>();
    private final Map<Integer, SlotAction> actions = new HashMap<>();

    public void setItem(int slot, ItemStack item) {
        items.put(slot, item);
    }

    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    public void setAction(int slot, SlotAction action) {
        actions.put(slot, action);
    }

    public SlotAction getAction(int slot) {
        return actions.get(slot);
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public Map<Integer, SlotAction> getActions() {
        return actions;
    }
}
