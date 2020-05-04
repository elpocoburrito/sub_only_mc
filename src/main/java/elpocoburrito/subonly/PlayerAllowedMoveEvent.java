package elpocoburrito.subonly;

import com.alessiodp.lastloginapi.api.LastLogin;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginAPI;
import com.alessiodp.lastloginapi.api.interfaces.LastLoginPlayer;
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
        if (javaplugin.getServer().getPluginManager().getPlugin("LastLoginAPI") != null) {
            if (javaplugin.getServer().getPluginManager().getPlugin("LastLoginAPI").isEnabled()) {
                LastLoginAPI api = LastLogin.getApi();

                LastLoginPlayer player = api.getPlayer(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(String.valueOf(player.getLastLogin()));
                String name = player.getName(); // Get the name
            }
        }
        if (!event.getPlayer().hasPermission("subonly.subbed") && javaplugin.getConfig().getBoolean("enabled"))
            event.setCancelled(true);
        else {
            event.getPlayer().kickPlayer("You are not subbed");
        }
    }
}
