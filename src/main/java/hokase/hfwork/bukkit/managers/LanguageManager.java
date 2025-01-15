package hokase.hfwork.bukkit.managers;

import hokase.hfwork.bukkit.api.ConfigAPI;

/**
 * Gerenciador de mensagens de idioma.
 */
public class LanguageManager {

    private final ConfigAPI config;
    private final ConfigAPI defaultConfig;

    public LanguageManager(ConfigAPI config, ConfigAPI defaultConfig) {
        this.config = config;
        this.defaultConfig = defaultConfig;
    }

    /**
     * Obtém uma mensagem no idioma atual.
     *
     * @param key A chave da mensagem.
     * @return A mensagem traduzida.
     */
    public String getMessage(String key) {
        String message = config.getString(key, null);
        if (message == null) {
            message = defaultConfig.getString(key, "&cMensagem não encontrada: " + key);
        }
        return message.replace("&", "§");
    }

    /**
     * Obtém uma mensagem no idioma atual com placeholders substituídos.
     *
     * @param key A chave da mensagem.
     * @param placeholders Os placeholders a serem substituídos (ex: "%player%", "Jogador1").
     * @return A mensagem traduzida com placeholders substituídos.
     */
    public String getMessage(String key, Object... placeholders) {
        String message = getMessage(key);
        for (int i = 0; i < placeholders.length; i += 2) {
            if (i + 1 < placeholders.length) {
                message = message.replace(placeholders[i].toString(), placeholders[i + 1].toString());
            }
        }
        return message;
    }
}