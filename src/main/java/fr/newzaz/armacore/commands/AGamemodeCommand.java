package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AGamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(AMessageUtils.MESSAGE_INSTANCEPLAYER);
        }

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("gmc")) {
            if (!p.hasPermission(APermissionUtils.PERMISSION_GAMEMODE_CREATIVE) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_GAMEMODE_CREATIVE));
                return true;
            }
            if (args.length == 0) {
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    p.sendMessage("§cLe joueur introuvable");
                    return true;
                }
                target.setGameMode(GameMode.CREATIVE);
                p.sendMessage("§9Statut du gamemode de " + target.getName() + " : [§c" + p.getGameMode().toString() + "§9]");
                target.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");

            }
        }
        if(cmd.getName().equalsIgnoreCase("gms")) {
            if (!p.hasPermission(APermissionUtils.PERMISSION_GAMEMODE_SURVIVAL) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()) {
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%", APermissionUtils.PERMISSION_GAMEMODE_SPECTATEUR));
                return true;
            }
            if (args.length == 0) {
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");
            }
            if (args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                p.sendMessage("§cLe joueur introuvable");
                return true;
            }
            target.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§9Statut du gamemode de " + target.getName() + " : [§c" + p.getGameMode().toString() + "§9]");
            target.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");
        }
        }
        if(cmd.getName().equalsIgnoreCase("gmsp")){
            if(!p.hasPermission(APermissionUtils.PERMISSION_GAMEMODE_SPECTATEUR) || !p.hasPermission(APermissionUtils.PERMISSION_ALL) || !p.isOp()){
                p.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_GAMEMODE_SPECTATEUR));
                return true;
            }
                if(args.length == 0) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");
                }

                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target == null) {
                        p.sendMessage("§cLe joueur introuvable");
                        return true;
                    }
                    target.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage("§9Statut du gamemode de " + target.getName() + " : [§c" + p.getGameMode().toString() + "§9]");
                    target.sendMessage("§9Statut du gamemode : [§c" + p.getGameMode().toString() + "§9]");
                }
        }
        return false;
    }
}
