package fr.newzaz.armacore.events;

import fr.newzaz.armacore.manager.AModerationManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class APlayerDropItemEvent implements Listener {
    AModerationManager pl = new AModerationManager();
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){

        if(pl.PlayerIsMod(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType() == Material.getMaterial(1)){
            e.setCancelled(true);
            e.getPlayer().sendMessage("t");
        }
    }
}
