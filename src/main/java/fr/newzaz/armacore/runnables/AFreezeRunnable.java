package fr.newzaz.armacore.runnables;

import fr.newzaz.armacore.commands.AModerationCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AFreezeRunnable extends BukkitRunnable {
    Player p;

    public AFreezeRunnable(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        if (AModerationCommand.FreezeUUID.contains(p.getUniqueId())) {
            this.p.sendMessage("§cVous êtes freeze venez discord : https://discord.gg/gdk5jAG");
        } else {
            this.cancel();
        }
    }
}
