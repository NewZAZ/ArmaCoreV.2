package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(AMessageUtils.MESSAGE_INSTANCEPLAYER);
            }

            Player p = (Player) sender;

            if (!p.hasPermission(APermissionUtils.PERMISSION_FLY) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_FLY));
                return true;
            }


            if (args.length == 0) {
                if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage("§9Statut Fly : [§cOFF§9]");
                        return true;
                    
                }else {
                    p.setAllowFlight(true);
                    p.sendMessage("§9Statut Fly : [§aON§9]");
                    return true;
                }

            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    p.sendMessage("§cLe joueur introuvable");
                    return true;
                }

                if (target.getAllowFlight()) {
                    if(target.isFlying()) {
                        target.setFlying(false);
                        p.sendMessage("§9Statut Fly : [§cOFF§9]");
                        p.sendMessage("§9Statut Fly de "+target.getName() +" : [§cOFF§9]");
                        return true;
                    }
                    target.setFlying(true);
                    target.sendMessage("§9Statut Fly : [§aON§9]");
                    p.sendMessage("§9Statut Fly de "+target.getName() +" : [§aON§9]");
                    return true;
                }else {
                    target.setAllowFlight(true);
                    return true;
                }

            }
        }

        return false;
    }
}
