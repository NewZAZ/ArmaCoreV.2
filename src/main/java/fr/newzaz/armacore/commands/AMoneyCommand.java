package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.data.APlayerData;
import fr.newzaz.armacore.utils.APermissionUtils;
import fr.newzaz.armacore.utils.IntegerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class AMoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cAttention tu doit être un joueur pour faire cette commande");
            return false;
        }
        Player p = (Player) sender;

        APlayerData data = new APlayerData(p.getName());

        if (!data.moneyExist()) {
            sender.sendMessage("§c(1) Une erreur vien de se produire veuillez contacter le développeur : NewZ_AZ#7736");
            data.createFileMoney();
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                p.sendMessage("§aBalance : " + data.getMoney() + "€");
                return false;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    p.sendMessage("§cArguments: /money [Player]");
                    return false;
                }
                APlayerData dataTarget = new APlayerData(target.getName());
                p.sendMessage("§a" + target.getName() + " Balance's : " + dataTarget.getMoney() + "€");
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("gmoney")) {
            if (p.hasPermission(APermissionUtils.PERMISSION_ALL) || p.hasPermission(APermissionUtils.PERMISSION_GIVE_MONEY)) {
                if (args.length == 0) {
                    p.sendMessage("§cArguments: /gmoney [Player] (Amount)");
                    return false;
                }
                if (args.length == 1) {
                    if (IntegerUtils.isInteger(args[0])) {
                        try {
                            data.setMoney(Integer.parseInt(args[0]));
                            p.sendMessage("§aTu vien de te donner " + Integer.parseInt(args[0]) + "€");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
                if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage("§cArguments: /gmoney [Player] (Amount)");
                        return false;
                    }
                    APlayerData dataTarget = new APlayerData(target.getName());
                    if (IntegerUtils.isInteger(args[1])) {
                        try {
                            dataTarget.setMoney(Integer.parseInt(args[1]));
                            target.sendMessage("§aUn §cAdministrateur §avien de te donner " + Integer.parseInt(args[1]) + "€");
                            p.sendMessage("§aTu vien de donner " + Integer.parseInt(args[1]) + "€ à " + target.getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("pay")) {
            if (args.length == 0 || args.length == 1) {
                p.sendMessage("§cArguments: /pay [Player] (Amount)");
                return false;
            }
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage("§cArguments: /gmoney [Player] (Amount)");
                    return false;
                }
                APlayerData dataTarget = new APlayerData(target.getName());

                if (IntegerUtils.isInteger(args[1])) {
                    try {
                        dataTarget.addMoney(Integer.parseInt(args[1]));
                        dataTarget.removeMoney(Integer.parseInt(args[1]));
                        target.sendMessage("§aTu vien de recevoir " + Integer.parseInt(args[1]) + "€ de " + p.getName());
                        p.sendMessage("§cTu vien d'envoyer " + Integer.parseInt(args[1]) + "€ à " + target.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("rmoney")) {
            if (p.hasPermission(APermissionUtils.PERMISSION_ALL) || p.hasPermission(APermissionUtils.PERMISSION_RESET_MONEY)) {
                if (args.length == 0) {
                    p.sendMessage("§cArguments: /rmoney [Player] ");
                    return false;
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage("§cArguments: /rmoney [Player] ");
                        return false;
                    }
                    APlayerData dataTarget = new APlayerData(target.getName());

                    try {
                        dataTarget.resetMoney();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    target.sendMessage("§cTon compte a été reset par un administrateur !");
                    p.sendMessage("§aTu as bien remis le compte à zero de " + target.getName());
                    return true;
                }
            }
        }
        return false;
    }


}
