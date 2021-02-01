package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.data.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class APlayerMoveEvent implements Listener {
    PlayerListener pl = new PlayerListener();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        if(pl.PlayerIsFreeze(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
