package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class APlayerMoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        if(AModerationCommand.FreezeUUID.contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
