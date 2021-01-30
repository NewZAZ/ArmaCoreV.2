package fr.newzaz.armacore.events;

import fr.newzaz.armacore.data.APlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class AAsyncPlayerChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        APlayerData data = new APlayerData(p.getName());

        if(!data.exist())return;
        if(!data.isTempmuted())return;

        if(data.getTempmuteMilliseconds() <= System.currentTimeMillis()){
            try {
                data.setUnTempmuted();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }else {
            p.sendMessage("§cDurée : "+new SimpleDateFormat("dd.MM.yyyy HH:mm").format(data.getTempmuteMilliseconds()));
            e.setCancelled(true);
        }
    }
}
