package lol.hyper.hotpotato.events;

import lol.hyper.hotpotato.HotPotato;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedHashSet;
import java.util.Set;

public class PlayerDropItem implements Listener {

    private final HotPotato hotPotato;

    public PlayerDropItem(HotPotato hotPotato) {
        this.hotPotato = hotPotato;
    }

    public Set<Player> dropAttempted = new LinkedHashSet<>();

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (dropAttempted.contains(player)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', hotPotato.config.getString("messages.drop")));
            dropAttempted.remove(player);
            return;
        }
        dropAttempted.add(player);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', hotPotato.config.getString("messages.attempt-drop")));
        int seconds = hotPotato.config.getInt("time-to-confirm");
        new BukkitRunnable() {
            public void run() {
                dropAttempted.remove(player);
            }
        }.runTaskLater(hotPotato, 20L * seconds);
    }
}
