package hokase.hfwork.models;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

@FunctionalInterface
public interface SlotAction {
    void onClick(Player player, ClickType clickType);
}
