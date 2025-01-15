package hokase.hfwork.bukkit.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * API para manipulação de arquivos de configuração.
 */
public class ConfigAPI {
    private final JavaPlugin plugin;
    private final File configFile;
    private final FileConfiguration config;

    public ConfigAPI(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Define um valor no arquivo de configuração.
     *
     * @param path  O caminho da configuração.
     * @param value O valor a ser definido.
     */
    public void set(String path, Object value) {
        config.set(path, value);
    }

    public String getString(String path, String defaultValue) {
        return config.contains(path) ? config.getString(path).replace("&", "§") : defaultValue;
    }

    public List<String> getStringList(String path, List<String> defaultValue) {
        return config.contains(path) ? config.getStringList(path).stream()
                .map(s -> s.replace("&", "§"))
                .collect(Collectors.toList()) : defaultValue;
    }

    public boolean getBoolean(String path, boolean defaultValue) {
        return config.contains(path) ? config.getBoolean(path) : defaultValue;
    }

    public int getInt(String path, int defaultValue) {
        return config.contains(path) ? config.getInt(path) : defaultValue;
    }

    public double getDouble(String path, double defaultValue) {
        return config.contains(path) ? config.getDouble(path) : defaultValue;
    }

    public long getLong(String path, long defaultValue) {
        return config.contains(path) ? config.getLong(path) : defaultValue;
    }

    public List<Integer> getIntList(String path) {
        return config.getIntegerList(path);
    }

    public void reloadConfig() {
        try {
            config.load(configFile);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Erro ao recarregar o arquivo " + configFile.getName(), e);
        }
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Erro ao salvar o arquivo " + configFile.getName(), e);
        }
    }
}