package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.data.PlayerListener;
import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AModerationCommand implements CommandExecutor {

    PlayerListener pl = new PlayerListener();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(AMessageUtils.MESSAGE_INSTANCEPLAYER);
            return false;
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("mod")) {


            if (!p.hasPermission(APermissionUtils.PERMISSION_MODERATION) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_MODERATION));
                return true;
            }
            if (pl.getModerationUUID().contains(p.getUniqueId())) {
                if (pl.getPlayerUUIDVanish().contains(p.getUniqueId())) {
                    pl.getModerationUUID().remove(p.getUniqueId());
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        t.showPlayer(p);
                    }
                    p.sendMessage("§9Vanish [§cOFF§9]");
                    return true;
                }
                pl.getModerationUUID().remove(p.getUniqueId());
                p.setInvulnerable(false);
                Bukkit.broadcastMessage("§7[§a+§7] §c" + p.getName() + " §7vien d'arriver sur le serveur !");
                p.sendMessage("§cVous avez quitter le mode modération !");
                p.getInventory().clear();
                p.getInventory().setContents(pl.getInventory().get(p.getName()));
                return true;
            }
            pl.getModerationUUID().add(p.getUniqueId());
            pl.getPlayerUUIDVanish().add(p.getUniqueId());

            for (Player t : Bukkit.getOnlinePlayers()) {
                t.hidePlayer(p);
            }
            p.sendMessage("§9Vanish [§aON§9]");
            p.setInvulnerable(true);
            Bukkit.broadcastMessage("§7[§a+§7] §c" + p.getName() + " §7vien de quitter le serveur !");
            p.sendMessage("§aVous venez de rentrer dans le mode modération !");
            pl.getInventory().put(p.getName(), p.getInventory().getContents());
            p.getInventory().clear();
            giveItem(p);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (!p.hasPermission(APermissionUtils.PERMISSION_VANISH) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_VANISH));
                return true;
            }
            if (pl.getPlayerUUIDVanish().contains(p.getUniqueId())) {
                pl.getPlayerUUIDVanish().remove(p.getUniqueId());
                for (Player t : Bukkit.getOnlinePlayers()) {
                    t.showPlayer(p);
                }
                p.sendMessage("§9Vanish [§cOFF§9]");
                return true;
            }

            pl.getPlayerUUIDVanish().add(p.getUniqueId());

            for (Player t : Bukkit.getOnlinePlayers()) {
                t.hidePlayer(p);
            }
            p.sendMessage("§9Vanish [§aON§9]");
        }

        return false;

    }

    private void giveItem(Player p) {

        p.getInventory().setItem(0, new ItemStack(Material.PACKED_ICE));
        p.getInventory().setItem(1, new ItemStack(Material.STICK));

    }

}
