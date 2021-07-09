package lol.hyper.hotpotato;

import lol.hyper.hotpotato.events.PlayerDropItem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class HotPotato extends JavaPlugin {

    public final File configFile = new File(this.getDataFolder(), "config.yml");
    public final Logger logger = this.getLogger();
    public final int CONFIG_VERSION = 1;
    public FileConfiguration config = this.getConfig();

    public PlayerDropItem playerDropItem;

    @Override
    public void onEnable() {
        playerDropItem = new PlayerDropItem(this);
        Bukkit.getPluginManager().registerEvents(playerDropItem, this);
        loadConfig();
    }

    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        if (config.getInt("config-version") != CONFIG_VERSION) {
            logger.warning("Your config file is outdated! Please regenerate the config.");
        }
    }
}
