package hokase.hfwork.bukkit;

import hokase.hfwork.bukkit.listeners.InventoryListener;
import hokase.hfwork.hFWorkAPI;
import hokase.hfwork.loader.hFWorkLoaderClass;
import org.bukkit.plugin.java.JavaPlugin;

public class hFWork extends JavaPlugin {

    public static hFWork instace;

    public void onEnable() {
        instace = this;
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }

    public void onDisable() {}

    public static hFWork hfInstace() {
        return instace;
    }
}
