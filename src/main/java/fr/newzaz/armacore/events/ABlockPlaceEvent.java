package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.data.PlayerListener;
import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ABlockPlaceEvent implements Listener {
    PlayerListener pl = new PlayerListener();
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if(pl.PlayerIsMod(p.getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(!p.hasPermission(APermissionUtils.PERMISSION_PLACE) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()){
            e.setCancelled(true);
            p.sendMessage(AMessageUtils.MESSAGE_PLACE);
        }
    }
}
