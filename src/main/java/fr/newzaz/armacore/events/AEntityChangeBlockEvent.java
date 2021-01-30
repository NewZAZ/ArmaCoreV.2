package fr.newzaz.armacore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;


public class AEntityChangeBlockEvent implements Listener {

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e){
        e.setCancelled(true);
    }
}
