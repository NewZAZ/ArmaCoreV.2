package fr.newzaz.armacore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class AExplosionPrimeEvent implements Listener {

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent e){
        e.setCancelled(true);
    }
}
