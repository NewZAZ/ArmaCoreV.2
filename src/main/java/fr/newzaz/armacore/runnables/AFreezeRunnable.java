package fr.newzaz.armacore.runnables;

import fr.newzaz.armacore.manager.AModerationManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AFreezeRunnable extends BukkitRunnable {
    Player p;

    public AFreezeRunnable(Player p) {
        this.p = p;
    }
    AModerationManager manager = new AModerationManager();
    @Override
    public void run() {
        if (manager.playerIsFreeze(p.getUniqueId())) {
            this.p.sendMessage("§cVous êtes freeze venez discord : https://discord.gg/gdk5jAG");
        } else {
            this.cancel();
        }
    }
}
