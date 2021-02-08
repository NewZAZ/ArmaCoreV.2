package fr.newzaz.armacore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class APlayerQuitEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if(p.isOp()){
            e.setQuitMessage("§7[§c-§7] §c" + p.getName() + " §7vien de quitter le serveur !");
        }
    }
}
