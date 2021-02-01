package fr.newzaz.armacore.events;

import fr.newzaz.armacore.Main;
import fr.newzaz.armacore.manager.AModerationManager;
import fr.newzaz.armacore.runnables.AFreezeRunnable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class APlayerInteractAtEntityEvent implements Listener {
    Main plugin;

    public APlayerInteractAtEntityEvent(Main plugin) {
        this.plugin = plugin;
    }

    AModerationManager manager = new AModerationManager();
    AModerationManager pl2 = new AModerationManager();
    @EventHandler
    public void onPlayerHitEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();

        if(manager.playerIsInMod(p.getUniqueId())){
            if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getType().equals(Material.PACKED_ICE)){
                Player target = (Player) e.getRightClicked();

                if(target != null && pl2.playerIsFreeze(target.getUniqueId())){
                    pl2.removePlayerInFreeze(target.getUniqueId());
                    return;
                }
                if(target != null){
                    pl2.addPlayerInFreeze(target.getUniqueId());
                    new AFreezeRunnable(p).runTaskTimer(plugin,20*15L,20*15L);
                }
            }
        }
    }
}
