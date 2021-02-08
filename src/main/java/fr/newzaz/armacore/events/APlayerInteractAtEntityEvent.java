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
    AModerationManager manager2 = new AModerationManager();
    @EventHandler
    public void onPlayerHitEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();

        if(p.getInventory().getItemInMainHand() == null)return;
        if(!p.getInventory().getItemInMainHand().hasItemMeta())return;
        Player target = (Player) e.getRightClicked();
        if(manager.playerIsInMod(p.getUniqueId())){
            if(p.getInventory().getItemInMainHand().getType().equals(Material.PACKED_ICE)){


                if(target != null && manager2.playerIsFreeze(target.getUniqueId())){
                    manager2.removePlayerInFreeze(target.getUniqueId());
                    p.sendMessage("§cVous venez d'unfreeze "+ target.getName());
                    return;
                }
                if(target != null){
                    manager2.addPlayerInFreeze(target.getUniqueId());
                    p.sendMessage("§aVous venez de freeze "+ target.getName());
                    new AFreezeRunnable(target).runTaskTimer(plugin,20*15L,20*15L);
                    return;
                }
            }
            if(p.getInventory().getItemInMainHand().getType().equals(Material.STICK)){
                if(target != null){
                    p.openInventory(target.getInventory());
                }
            }
        }
    }
}
