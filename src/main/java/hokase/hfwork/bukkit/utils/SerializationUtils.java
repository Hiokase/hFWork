package hokase.hfwork.bukkit.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;

/**
 * Utilitários para serialização e desserialização de itens.
 */
public class SerializationUtils {
    private static final Gson gson = new Gson();

    /**
     * Serializa um ItemStack para uma string.
     *
     * @param item O ItemStack a ser serializado.
     * @return A string serializada.
     */
    public static String serializeItemStack(ItemStack item) {
        String json = gson.toJson(item.serialize());
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    /**
     * Desserializa uma string para um ItemStack.
     *
     * @param data A string serializada.
     * @return O ItemStack desserializado.
     */
    public static ItemStack deserializeItemStack(String data) {
        String json = new String(Base64.getDecoder().decode(data));
        Type type = new TypeToken<ItemStack>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Serializa uma lista de ItemStacks para uma string.
     *
     * @param items A lista de ItemStacks a ser serializada.
     * @return A string serializada.
     */
    public static String serializeItemStackList(List<ItemStack> items) {
        String json = gson.toJson(items);
        return Base64.getEncoder().encodeToString(json.getBytes());
    }
}

