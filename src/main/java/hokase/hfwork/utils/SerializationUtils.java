package hokase.hfwork.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;

public class SerializationUtils {
    private static final Gson gson = new Gson();

    public static String serializeItemStack(ItemStack item) {
        String json = gson.toJson(item.serialize());
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    public static ItemStack deserializeItemStack(String data) {
        String json = new String(Base64.getDecoder().decode(data));
        Type type = new TypeToken<ItemStack>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static String serializeItemStackList(List<ItemStack> items) {
        String json = gson.toJson(items);
        return Base64.getEncoder().encodeToString(json.getBytes());
    }

    public static List<ItemStack> deserializeItemStackList(String data) {
        String json = new String(Base64.getDecoder().decode(data));
        Type type = new TypeToken<List<ItemStack>>() {}.getType();
        return gson.fromJson(json, type);
    }
}