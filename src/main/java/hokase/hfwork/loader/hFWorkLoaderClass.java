package hokase.hfwork.loader;

import hokase.hfwork.bukkit.hFWork;
import hokase.hfwork.loader.commands.RegisterCommands;
import hokase.hfwork.loader.getters.ClassScanner;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


@RequiredArgsConstructor
public class hFWorkLoaderClass {

    private final JavaPlugin plugin;
    private final RegisterCommands commandRegister;

    public hFWorkLoaderClass(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandRegister = new RegisterCommands();
    }

    public void loadCommands(String packageName) {
        load(packageName, LoadType.COMMAND);
    }

    public void loadListeners(String packageName) {
        load(packageName, LoadType.LISTENER);
    }

    public void loadAll(String packageName) {
        load(packageName, LoadType.ALL);
    }

    private void load(String packageName, LoadType loadType) {
        if (plugin == null || packageName == null) {
            throw new IllegalArgumentException("Plugin and package name must not be null!");
        }

        for (Class<?> clazz : ClassScanner.getClassesFromPackage(plugin, packageName)) {
            try {
                processClass(clazz, loadType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processClass(Class<?> clazz, LoadType loadType) throws InstantiationException, IllegalAccessException {
        Class<?> targetType = loadType.getTargetType();

        if (!clazz.getName().contains("$") && (targetType == null || targetType.isAssignableFrom(clazz))) {
            Object instance = clazz.newInstance();

            if (instance instanceof Listener) {
                plugin.getLogger().info("Listener loaded: " + clazz.getName());
                Bukkit.getPluginManager().registerEvents((Listener) instance, plugin);
            } else if (instance instanceof Command) {
                Command command = (Command) instance;
                plugin.getLogger().info("Command loaded: " + command.getName());
                commandRegister.register(plugin, command);
            }
        }
    }

    @Getter
    public enum LoadType {
        COMMAND(Command.class), LISTENER(Listener.class), ALL(null);

        private final Class<?> targetType;

        LoadType(Class<?> targetType) {
            this.targetType = targetType;
        }

    }
}
