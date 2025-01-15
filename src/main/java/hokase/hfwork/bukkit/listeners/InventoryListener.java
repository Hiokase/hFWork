package hokase.hfwork.bukkit.listeners;

import hokase.hfwork.bukkit.inventorys.guis.InventoryGUI;
import hokase.hfwork.bukkit.inventorys.guis.PaginatedGUI;
import hokase.hfwork.bukkit.models.SlotAction;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Listener para eventos de inventário.
 */

public class InventoryListener implements Listener {
    private static final Map<Inventory, InventoryGUI> guis = new HashMap<>();
    private static final Map<Inventory, PaginatedGUI> paginatedGuis = new HashMap<>();

    /**
     * Registra um inventário personalizado.
     *
     * @param gui O inventário personalizado a ser registrado.
     */
    public static void registerInventory(InventoryGUI gui) {
        guis.put(gui.getInventory(), gui);
    }


    /**
     * Registra um inventário paginado.
     *
     * @param gui O inventário paginado a ser registrado.
     */
    public static void registerInventory(PaginatedGUI gui) {
        paginatedGuis.put(gui.getCurrentPage(), gui);
    }



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (guis.containsKey(inventory)) {
            event.setCancelled(true);
            SlotAction action = guis.get(inventory).getActions().get(event.getRawSlot());
            if (action != null) {
                action.onClick((Player) event.getWhoClicked(), event.getClick());
            }
        } else if (paginatedGuis.containsKey(inventory)) {
            event.setCancelled(true);
            SlotAction action = paginatedGuis.get(inventory).getActions().get(event.getRawSlot());
            if (action != null) {
                action.onClick((Player) event.getWhoClicked(), event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        guis.remove(inventory);
        paginatedGuis.remove(inventory);
    }
}