package hokase.hfwork;

import hokase.hfwork.bukkit.hFWork;
import hokase.hfwork.loader.hFWorkLoaderClass;
import org.bukkit.plugin.java.JavaPlugin;

public class hFWorkAPI {

   public hFWorkLoaderClass getHFWorkLoaderClass(JavaPlugin plugin) {
       return new hFWorkLoaderClass(plugin);
   }

   public hFWork getPlugin(){
       return hFWork.hfInstace();
   }

}
