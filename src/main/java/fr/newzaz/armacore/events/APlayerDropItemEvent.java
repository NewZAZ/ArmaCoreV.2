package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class APlayerDropItemEvent implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e){

        if(AModerationCommand.ModerationUUID.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(e.getItemDrop().getItemStack().getType() == Material.getMaterial(1)){
            e.setCancelled(true);
            e.getPlayer().sendMessage("t");
        }
    }
}
