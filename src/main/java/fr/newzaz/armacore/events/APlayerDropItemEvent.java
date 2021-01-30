package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.data.PlayerListener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class APlayerDropItemEvent implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){
        PlayerListener pl = new PlayerListener();
        if(pl.getModerationUUID().contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType() == Material.getMaterial(1)){
            e.setCancelled(true);
            e.getPlayer().sendMessage("t");
        }
    }
}
