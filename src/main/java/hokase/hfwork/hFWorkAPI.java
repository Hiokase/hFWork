package hokase.hfwork;

import hokase.hfwork.bukkit.hFWork;
import hokase.hfwork.loader.hFWorkLoaderClass;
import org.bukkit.plugin.java.JavaPlugin;

public class hFWorkAPI {

    public hFWorkLoaderClass createLoader(JavaPlugin plugin) {
        return new hFWorkLoaderClass(plugin);
    }

    public hFWork getCore() {
        return hFWork.hfInstace();
    }
}
