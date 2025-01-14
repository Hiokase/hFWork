package hokase.hfwork.events;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEvent {
    private final Player player;
    private final ItemStack item;
    private final ClickType clickType;

    public InventoryClickEvent(Player player, ItemStack item, ClickType clickType) {
        this.player = player;
        this.item = item;
        this.clickType = clickType;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public ClickType getClickType() {
        return clickType;
    }
}
