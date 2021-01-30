package fr.newzaz.armacore.runnables;

import fr.newzaz.armacore.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ABBRunnable extends BukkitRunnable {

    Main plugin;
    int x, y, z;
    Player p;
    Block b;

    public ABBRunnable(Main plugin, int x, int y, int z, Player p, Block b) {
        this.plugin = plugin;
        this.x = x;
        this.y = y;
        this.z = z;
        this.p = p;
        this.b = b;
    }

    @Override
    public void run() {
        p.getWorld().getBlockAt(x, y, z).setType(b.getType());
        this.cancel();
    }
}
