package hokase.hfwork;

import hokase.hfwork.bukkit.hFWork;
import hokase.hfwork.loader.hFWorkLoaderClass;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * API principal da framework hFWork.
 */
public class hFWorkAPI {

    /**
     * Cria um carregador de componentes.
     *
     * @param plugin O plugin principal.
     * @return Uma instância de hFWorkLoaderClass.
     */
    public hFWorkLoaderClass createLoader(JavaPlugin plugin) {
        return new hFWorkLoaderClass(plugin);
    }

    /**
     * Retorna a instância principal do plugin.
     *
     * @return A instância principal do plugin.
     */
    public hFWork hFWork() {
        return hFWork.hfInstace();
    }
}