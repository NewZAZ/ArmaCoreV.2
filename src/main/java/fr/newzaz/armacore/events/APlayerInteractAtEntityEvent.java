package fr.newzaz.armacore.events;

import fr.newzaz.armacore.Main;
import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.data.PlayerListener;
import fr.newzaz.armacore.runnables.AFreezeRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class APlayerInteractAtEntityEvent implements Listener {
    Main plugin;

    public APlayerInteractAtEntityEvent(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerHitEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        PlayerListener pl = new PlayerListener();
        if(pl.getModerationUUID().contains(p.getUniqueId())){
            if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getType().equals(Material.PACKED_ICE)){
                Player target = (Player) e.getRightClicked();

                if(target != null && pl.getFreezeUUID().contains(target.getUniqueId())){
                    pl.getFreezeUUID().remove(target.getUniqueId());
                    return;
                }
                if(target != null){
                    pl.getFreezeUUID().add(target.getUniqueId());
                    new AFreezeRunnable(p).runTaskTimer(plugin,20*15L,20*15L);
                }
            }
        }
    }
}
