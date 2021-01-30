package fr.newzaz.armacore.events;

import fr.newzaz.armacore.Main;
import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.runnables.ABBRunnable;
import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ABlockBreakEvent implements Listener {

    Main plugin;

    public ABlockBreakEvent(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player p = e.getPlayer();
        Block b = e.getBlock();
        if(AModerationCommand.ModerationUUID.contains(p.getUniqueId())){
            e.setCancelled(true);
            return;
        }
        if(b.getType() == Material.IRON_ORE){
            startRunnable(plugin, b.getX(), b.getY(), b.getZ(), p, b);
            return;
        }
        if(!p.hasPermission(APermissionUtils.PERMISSION_BREAK) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()){
            e.setCancelled(true);
            p.sendMessage(AMessageUtils.MESSAGE_BREAK);
        }
    }

    public void startRunnable(Main main, int x, int y, int z, Player player, Block block){
        new ABBRunnable(main,x,y,z,player,block).runTaskLater(main,20*5L);
    }
}
