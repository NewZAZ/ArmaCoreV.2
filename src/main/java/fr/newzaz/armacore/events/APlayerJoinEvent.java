package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class APlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        for(Player pv : Bukkit.getOnlinePlayers()){
            if(AModerationCommand.playerUUIDVanish.contains(pv.getUniqueId())){
                p.hidePlayer(pv);
            }
        }
        if(p.isOp()){
            e.setJoinMessage("§7[§a+§7] §c"+ p.getName() +" §7vien d'arriver sur le serveur !");
            return;
        }
        e.setJoinMessage("");
    }
}
