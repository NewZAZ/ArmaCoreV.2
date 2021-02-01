package fr.newzaz.armacore.events;

import fr.newzaz.armacore.manager.AModerationManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class APlayerMoveEvent implements Listener {
    AModerationManager manager = new AModerationManager();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        if(manager.playerIsFreeze(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
