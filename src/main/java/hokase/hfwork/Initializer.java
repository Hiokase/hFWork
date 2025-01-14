package hokase.hfwork;

import hokase.hfwork.listeners.InventoryListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Initializer extends JavaPlugin {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    public void onDisable() {}
}
