package hokase.hfwork.bukkit.models;

import hokase.hfwork.bukkit.api.ConfigAPI;
import hokase.hfwork.bukkit.managers.LanguageManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Gerenciador de configurações e idiomas.
 */
public class ConfigModel {

    private static ConfigModel instance;
    private final JavaPlugin plugin;
    private final Map<String, ConfigAPI> configFiles = new HashMap<>();
    private String currentLanguage = "pt-br"; // Idioma padrão

    private ConfigModel(JavaPlugin plugin) {
        this.plugin = plugin;
        loadLanguageSetting(); // Carrega o idioma salvo ao iniciar
    }

    /**
     * Inicializa o ConfigManager.
     *
     * @param plugin O plugin principal.
     */
    public static void initialize(JavaPlugin plugin) {
        if (instance == null) {
            instance = new ConfigModel(plugin);
        }
    }

    /**
     * Retorna a instância do ConfigManager.
     *
     * @return A instância do ConfigManager.
     */
    public static ConfigModel getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigManager não foi iniciado!");
        }
        return instance;
    }

    /**
     * Adiciona um novo arquivo de configuração.
     *
     * @param fileName O nome do arquivo de configuração.
     */
    public void addConfig(String fileName) {
        File configFile = new File(plugin.getDataFolder(), fileName);
        File parentDir = configFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!configFile.exists()) {
            try (InputStream in = plugin.getResource(fileName)) {
                if (in != null) {
                    Files.copy(in, configFile.toPath());
                    plugin.getLogger().info("Arquivo " + fileName + " criado com sucesso.");
                } else {
                    plugin.getLogger().warning("Recurso " + fileName + " não encontrado no plugin.");
                }
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Erro ao criar o arquivo " + fileName, e);
            }
        }

        configFiles.put(fileName, new ConfigAPI(plugin, fileName));
    }

    /**
     * Obtém um arquivo de configuração.
     *
     * @param fileName O nome do arquivo de configuração.
     * @return A instância de ConfigAPI correspondente.
     */
    public ConfigAPI getConfig(String fileName) {
        ConfigAPI config = configFiles.get(fileName);
        if (config == null) {
            plugin.getLogger().warning("Configuração " + fileName + " não encontrada.");
        }
        return config;
    }

    /**
     * Define o idioma padrão e salva no arquivo de configuração.
     *
     * @param language O idioma a ser definido como padrão.
     */
    public void setDefaultLanguage(String language) {
        this.currentLanguage = language;
        saveLanguageSetting(); // Salva o idioma no arquivo de configuração
    }

    /**
     * Obtém o gerenciador de idiomas.
     *
     * @return O gerenciador de idiomas.
     */
    public LanguageManager getLanguageManager() {
        ConfigAPI langConfig = getConfig("messages/messages_" + currentLanguage + ".yml");
        ConfigAPI defaultLangConfig = getConfig("messages/messages_en.yml"); // Idioma padrão de fallback
        return new LanguageManager(langConfig, defaultLangConfig);
    }

    /**
     * Carrega o idioma salvo no arquivo de configuração.
     */
    private void loadLanguageSetting() {
        ConfigAPI config = getConfig("config.yml");
        if (config != null) {
            this.currentLanguage = config.getString("language", "pt-br");
        }
    }

    /**
     * Salva o idioma no arquivo de configuração.
     */
    private void saveLanguageSetting() {
        ConfigAPI config = getConfig("config.yml");
        if (config != null) {
            config.set("language", currentLanguage); // Usa o método set para definir o valor
            config.saveConfig(); // Salva o arquivo de configuração
        }
    }
}