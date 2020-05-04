package elpocoburrito.subonly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEventHandlers implements Listener {
    JavaPlugin javaplugin;

    public PlayerEventHandlers(JavaPlugin javaplugin){
        this.javaplugin = javaplugin;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        if (!event.getPlayer().hasPermission("subonly.subbed") && javaplugin.getConfig().getBoolean("enabled"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {


        new BukkitRunnable() {
            Player p = event.getPlayer();

            @Override
            public void run() {
                if (p.hasPermission("validated")) {
                    if (!p.hasPermission("subonly.subbed"))
                        p.kickPlayer("The Server is in Sub Only Mode");
                } else {
                    p.sendMessage("Please Link Your Discord");

                    new BukkitRunnable() {
                        Player pL = p;

                        @Override
                        public void run() {
                            if (!pL.hasPermission("subonly.subbed")) {
                                pL.kickPlayer("The Server is in Sub Only Mode");
                            }
                        }
                    }.runTaskLater(javaplugin, 6000);
                }
            }
        }.runTaskLater(javaplugin, 40);
    }
}
