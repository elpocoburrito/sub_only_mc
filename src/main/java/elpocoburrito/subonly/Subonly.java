package elpocoburrito.subonly;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class Subonly extends JavaPlugin {
    private boolean isStreaming = false;

    @Override
    public void onEnable() {
        FileConfiguration config = this.getConfig();
        config.addDefault("enabled", false);
        config.options().copyDefaults();
        config.set("enabled", getConfig().getBoolean("enabled"));
        saveConfig();
        if (this.getServer().getPluginManager().getPermission("subonly.subbed") == null) {
            this.getServer().getPluginManager().addPermission(new Permission("subonly.subbed"));
        }
        this.getServer().getPluginManager().registerEvents(new PlayerEventHandlers(this),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean getStreaming()
    {
        return isStreaming;
    }
}
