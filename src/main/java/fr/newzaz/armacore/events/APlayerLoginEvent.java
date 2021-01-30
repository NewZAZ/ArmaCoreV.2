package fr.newzaz.armacore.events;

import fr.newzaz.armacore.data.APlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class APlayerLoginEvent implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e){

        Player p = e.getPlayer();

        APlayerData data = new APlayerData(p.getName());

        if(!data.exist())return;
        if(!data.isTempbanned())return;

        if(data.getTempbanMilliseconds() <= System.currentTimeMillis()){
            try {
                data.setUnTempbanned();
            } catch (IOException io) {
                io.printStackTrace();
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER,"Un problème a était trouvé ! veuillez contactez le staff !");
            }
            return;
        }else {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER,"§c- ArmaCraft -\n\n §cSanction : Ban\n §cAuteur : "+ data.getTempbannedFrom()+"\n §cpour : "+ data.getTempbannedReason()+ "\n§cDurée : "+ new SimpleDateFormat("dd.MM.yyyy HH:mm").format(data.getTempbanMilliseconds()));
        }
    }
}
