package hokase.hfwork.bukkit.inventorys;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Utilitários para manipulação de inventários.
 */
public class InventoryUtils {

    /**
     * Preenche todos os slots vazios de um inventário com um item específico.
     *
     * @param inventory O inventário a ser preenchido.
     * @param item O item a ser usado para preencher os slots.
     */
    public static void fillInventory(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    /**
     * Adiciona um item em um slot específico do inventário.
     *
     * @param inventory O inventário onde o item será adicionado.
     * @param item O item a ser adicionado.
     * @param slot O slot onde o item será adicionado.
     */
    public static void addItem(Inventory inventory, ItemStack item, int slot) {
        inventory.setItem(slot, item);
    }

    /**
     * Adiciona um item ao inventário.
     *
     * @param inventory O inventário onde o item será adicionado.
     * @param item O item a ser adicionado.
     */
    public static void addItem(Inventory inventory, ItemStack item) {
        inventory.addItem(item);
    }
}