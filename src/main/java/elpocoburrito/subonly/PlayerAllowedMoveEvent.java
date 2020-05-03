package elpocoburrito.subonly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerAllowedMoveEvent implements Listener {
    JavaPlugin javaplugin;

    public PlayerAllowedMoveEvent(JavaPlugin javaplugin){
        this.javaplugin = javaplugin;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        if (!event.getPlayer().hasPermission("subonly.subbed") && javaplugin.getConfig().getBoolean("enabled"))
            event.setCancelled(true);
    }
}
