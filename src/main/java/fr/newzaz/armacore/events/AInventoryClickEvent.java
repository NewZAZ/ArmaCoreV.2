package fr.newzaz.armacore.events;

import fr.newzaz.armacore.commands.AModerationCommand;
import fr.newzaz.armacore.manager.AModerationManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AInventoryClickEvent implements Listener {
    AModerationManager manager = new AModerationManager();


    @EventHandler
    public void onPlayerClickOnInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(manager.playerIsInMod(p.getUniqueId())){
            e.setCancelled(true);
        }
    }
}
