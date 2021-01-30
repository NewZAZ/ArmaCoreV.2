package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.data.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class APlayerMoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        PlayerListener pl = new PlayerListener();
        if(pl.getFreezeUUID().contains(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
        }
    }
}
