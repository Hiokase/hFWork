package hokase.hfwork.bukkit.inventorys.guis;

import hokase.hfwork.bukkit.listeners.InventoryListener;
import hokase.hfwork.bukkit.models.SlotAction;
import hokase.hfwork.bukkit.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Representa um inventário paginado.
 */
public class PaginatedGUI {
    private final String title;
    private final int size;
    private final Function<Integer, List<ItemStack>> itemProvider;
    private int currentPage = 0;
    /**
     * -- GETTER --
     *  Retorna as ações dos slots.
     *
     * @return Um mapa de ações dos slots.
     */

    private final Map<Integer, SlotAction> actions = new HashMap<>();

    /**
     * Cria um novo inventário paginado.
     *
     * @param title Título do inventário.
     * @param size Tamanho do inventário.
     * @param itemProvider Função que fornece os itens para cada página.
     */
    public PaginatedGUI(String title, int size, Function<Integer, List<ItemStack>> itemProvider) {
        this.title = title;
        this.size = size;
        this.itemProvider = itemProvider;
    }

    public Map<Integer, SlotAction> getActions() {
        return actions;
    }

    /**
     * Abre o inventário para um jogador.
     *
     * @param player O jogador para quem o inventário será aberto.
     */
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

    /**
     * Avança para a próxima página.
     */
    public void nextPage() {
        currentPage++;
    }

    /**
     * Volta para a página anterior.
     */
    public void previousPage() {
        currentPage--;
    }

    /**
     * Retorna o inventário da página atual.
     *
     * @return O inventário da página atual.
     */
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

}