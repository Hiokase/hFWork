package hokase.hfwork.bukkit.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Utilitário para construção de itens personalizados.
 */
public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    /**
     * Cria um novo ItemBuilder com o material especificado.
     *
     * @param material O material do item.
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Define o nome de exibição do item.
     *
     * @param name O nome de exibição.
     * @return O ItemBuilder atual.
     */
    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    /**
     * Define a lore do item.
     *
     * @param lore A lore do item.
     * @return O ItemBuilder atual.
     */
    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Define a lore do item.
     *
     * @param lore A lore do item.
     * @return O ItemBuilder atual.
     */
    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    /**
     * Adiciona um encantamento ao item.
     *
     * @param enchantment O encantamento a ser adicionado.
     * @param level O nível do encantamento.
     * @return O ItemBuilder atual.
     */
    public ItemBuilder addEnchant(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Constrói o item.
     *
     * @return O ItemStack construído.
     */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}