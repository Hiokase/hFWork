package hokase.hfwork.bukkit.api;

import hokase.hfwork.bukkit.inventorys.guis.InventoryGUI;
import hokase.hfwork.bukkit.inventorys.guis.PaginatedGUI;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;

/**
 * API para criação de inventários personalizados.
 */
public class InventoryAPI {

    /**
     * Cria um inventário simples.
     *
     * @param title Título do inventário.
     * @param size Tamanho do inventário.
     * @param items Itens a serem adicionados ao inventário.
     * @return Uma instância de InventoryGUI.
     */
    public static InventoryGUI createInventory(String title, int size, List<ItemStack> items) {
        return new InventoryGUI(title, size, items);
    }

    /**
     * Cria um inventário paginado.
     *
     * @param title Título do inventário.
     * @param size Tamanho do inventário.
     * @param itemProvider Função que fornece os itens para cada página.
     * @return Uma instância de PaginatedGUI.
     */
    public static PaginatedGUI createPaginatedInventory(String title, int size, Function<Integer, List<ItemStack>> itemProvider) {
        return new PaginatedGUI(title, size, itemProvider);
    }
}