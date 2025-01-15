package hokase.hfwork.bukkit.inventorys.guis;

import hokase.hfwork.bukkit.listeners.InventoryListener;
import hokase.hfwork.bukkit.models.SlotAction;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa um inventário personalizado.
 */
@Getter
public class InventoryGUI {
    /**
     * -- GETTER --
     *  Retorna o inventário.
     *
     * @return O inventário.
     */
    private final Inventory inventory;
    /**
     * -- GETTER --
     *  Retorna as ações dos slots.
     *
     * @return Um mapa de ações dos slots.
     */
    private final Map<Integer, SlotAction> actions = new HashMap<>();

    /**
     * Cria um novo inventário personalizado.
     *
     * @param title Título do inventário.
     * @param size Tamanho do inventário.
     * @param items Itens a serem adicionados ao inventário.
     */
    public InventoryGUI(String title, int size, List<ItemStack> items) {
        this.inventory = Bukkit.createInventory(null, size, title);
        for (int i = 0; i < items.size(); i++) {
            inventory.setItem(i, items.get(i));
        }
    }

    /**
     * Define uma ação para um slot específico.
     *
     * @param slot O slot onde a ação será definida.
     * @param action A ação a ser executada ao clicar no slot.
     */
    public void setAction(int slot, SlotAction action) {
        actions.put(slot, action);
    }

    /**
     * Abre o inventário para um jogador.
     *
     * @param player O jogador para quem o inventário será aberto.
     */
    public void open(Player player) {
        InventoryListener.registerInventory(this);
        player.openInventory(inventory);
    }

}