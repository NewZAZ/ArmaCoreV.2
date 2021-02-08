package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.manager.AModerationManager;
import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AModerationCommand implements CommandExecutor {

    AModerationManager manager = new AModerationManager();

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
            if (manager.playerIsInMod(p.getUniqueId())) {
                manager.removePlayerInMod(p.getUniqueId());
                p.setInvulnerable(false);
                Bukkit.broadcastMessage("§7[§a+§7] §c" + p.getName() + " §7vien d'arriver sur le serveur !");
                p.sendMessage("§cVous avez quitter le mode modération !");
                p.getInventory().clear();
                p.getInventory().setContents(manager.getInventory(p.getUniqueId()));
                return true;
            }
                manager.addPlayerInMod(p.getUniqueId());
                p.setInvulnerable(true);
                Bukkit.broadcastMessage("§7[§c-§7] §c" + p.getName() + " §7vien de quitter le serveur !");
                p.sendMessage("§aVous venez de rentrer dans le mode modération !");
                manager.setInventoryContents(p.getInventory().getContents(),p.getUniqueId());
                giveItem(p);
                return true;
        }

        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (!p.hasPermission(APermissionUtils.PERMISSION_VANISH) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_VANISH));
                return true;
            }
            if (manager.playerIsVanished(p.getUniqueId())) {
                manager.removePlayerInVanish(p.getUniqueId());
                for (Player t : Bukkit.getOnlinePlayers()) {
                    t.showPlayer(p);
                }
                p.sendMessage("§9Vanish [§cOFF§9]");
                return true;
            }

            manager.addPlayerInVanish(p.getUniqueId());

            for (Player t : Bukkit.getOnlinePlayers()) {
                t.hidePlayer(p);
            }
            p.sendMessage("§9Vanish [§aON§9]");
        }

        return false;

    }

    private void giveItem(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, new ItemStack(Material.PACKED_ICE));
        p.getInventory().setItem(1, new ItemStack(Material.STICK));
        p.getInventory().setItem(2, new ItemStack(Material.SLIME_BALL));

    }



}
