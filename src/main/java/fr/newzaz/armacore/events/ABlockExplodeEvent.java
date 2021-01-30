package fr.newzaz.armacore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;

public class ABlockExplodeEvent implements Listener {

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e){

        if(e.getBlock().getWorld().getName().equalsIgnoreCase("ArmaCraftMAP")){
            e.setCancelled(true);
        }
    }
}
