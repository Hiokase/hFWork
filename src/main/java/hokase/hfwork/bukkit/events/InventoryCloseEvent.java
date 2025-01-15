package hokase.hfwork.bukkit.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
@Getter
public class InventoryCloseEvent {
    private final Player player;
    private final Inventory inventory;

    public InventoryCloseEvent(Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

}
