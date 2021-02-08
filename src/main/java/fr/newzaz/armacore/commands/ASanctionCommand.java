package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.data.APlayerData;
import fr.newzaz.armacore.utils.AMessageUtils;
import fr.newzaz.armacore.utils.APermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ASanctionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("tempban")) {
            if(!sender.hasPermission(APermissionUtils.PERMISSION_TEMPBAN) || !sender.hasPermission(APermissionUtils.PERMISSION_ALL) || !sender.isOp()){
                sender.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_TEMPBAN));
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§c/tempban <player> <temps> [raison]");
                return true;
            }

            String format = args[1].substring(args[1].length() - 1);

            long duration = Integer.parseInt(args[1].substring(0, args[1].length() - 1));

            long time = 0;

            switch (format) {
                case "s":
                    time = duration * 1000;
                    break;
                case "m":
                    time = duration * 1000 * 60;
                    break;
                case "h":
                    time = duration * 1000 * 60 * 60;
                    break;
                case "d":
                    time = duration * 1000 * 60 * 60 * 24;
                    break;
                case "w":
                    time = duration * 1000 * 60 * 60 * 24 * 7;
                    break;
                default:
                    sender.sendMessage("§cCe n'est pas le bon format exemple: /tempban test 1s ou 1m ou 1h ou 1d ou 1w bantest");
                    return true;
            }

            String reason = "";

            for (int i = 2; i < args.length; i++) {
                reason = reason + args[i] + "";
            }
            reason = reason.trim();

            APlayerData data = new APlayerData(getPlayerFromName(args[0]));

            if (!data.exist()) {
                sender.sendMessage("§c(1) Une erreur vien de se produire veuillez contacter le développeur : NewZ_AZ#7736");
                data.createFile();
                return true;
            }

            try {
                data.setTempbanned(getPlayerFromName(args[0]), reason.isEmpty() ? "Ban temporaire" : reason, time);
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("§c(1) Une erreur vien de se produire !");
            }

            if (Bukkit.getPlayer(getPlayerFromName(args[0])) != null) {
                Bukkit.getPlayer(getPlayerFromName(args[0])).kickPlayer("§c- ArmaCraft -\n\n Sanction : ban\n Auteur : " + data.getTempbannedFrom() + "\n pour : " + data.getTempbannedReason() + "\nDurée : " + data.getTempbanTimestamp() + data.getTempbanMilliseconds());
            }

            Bukkit.broadcastMessage("§c(!) " + getPlayerFromName(args[0]) + " a été ban par " + sender.getName() + " pour " + reason);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("unban")) {

            if(!sender.hasPermission(APermissionUtils.PERMISSION_UNBAN) || !sender.hasPermission(APermissionUtils.PERMISSION_ALL) || !sender.isOp()){
                sender.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_UNBAN));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage("§c/unban <player>");
            }
            if (args.length == 1) {
                APlayerData data = new APlayerData(getPlayerFromName(args[0]));

                if (!data.exist()) {
                    sender.sendMessage("§c(2) Une erreur vien de se produire veuillez contacter le développeur : NewZ_AZ#7736");
                    data.createFile();
                    return true;
                }

                if (!data.isTempbanned()) {
                    sender.sendMessage("§c" + getPlayerFromName(args[0]) + " n'est pas banni !");
                    return true;
                }

                try {
                    data.setUnTempbanned();
                } catch (IOException e) {
                    e.printStackTrace();
                    sender.sendMessage("§c(2) Une erreur vien de se produire !");
                }
                Bukkit.broadcastMessage("§c(!) " + getPlayerFromName(args[0]) + " a été unban par " + sender.getName());


            }
        }


        if (cmd.getName().equalsIgnoreCase("tempmute")) {
            if(!sender.hasPermission(APermissionUtils.PERMISSION_TEMPMUTE) || !sender.hasPermission(APermissionUtils.PERMISSION_ALL) || !sender.isOp()){
                sender.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_TEMPMUTE));
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage("§c/tempmute <player> <temps> [raison]");
                return true;
            }

            String format = args[1].substring(args[1].length() - 1);

            long duration = Integer.parseInt(args[1].substring(0, args[1].length() - 1));

            long time = 0;

            switch (format) {
                case "s":
                    time = duration * 1000;
                    break;
                case "m":
                    time = duration * 1000 * 60;
                    break;
                case "h":
                    time = duration * 1000 * 60 * 60;
                    break;
                case "d":
                    time = duration * 1000 * 60 * 60 * 24;
                    break;
                case "w":
                    time = duration * 1000 * 60 * 60 * 24 * 7;
                    break;
                default:
                    sender.sendMessage("§cCe n'est pas le bon format exemple: /tempmute test 1s ou 1m ou 1h ou 1d ou 1w mutetest");
                    return true;
            }

            String reason = "";

            for (int i = 2; i < args.length; i++) {
                reason += args[i] + "";
            }
            reason = reason.trim();

            APlayerData data = new APlayerData(getPlayerFromName(args[0]));

            if (!data.exist()) {
                sender.sendMessage("§c(1) Une erreur vien de se produire veuillez contacter le développeur : NewZ_AZ#7736");
                data.createFile();
                return true;
            }

            try {
                data.setTempmuted(getPlayerFromName(args[0]), reason.isEmpty() ? "Mute temporaire" : reason, time);
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("§c(1) Une erreur vien de se produire !");
            }

            Bukkit.broadcastMessage("§c(!) " + getPlayerFromName(args[0]) + " a été mute par " + sender.getName() + " pour " + reason);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("unmute")) {

            if(!sender.hasPermission(APermissionUtils.PERMISSION_UNMUTE) || !sender.hasPermission(APermissionUtils.PERMISSION_ALL) || !sender.isOp()){
                sender.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_UNMUTE));
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage("§c/unban <player>");
            }
            if (args.length == 1) {
                APlayerData data = new APlayerData(getPlayerFromName(args[0]));

                if (!data.exist()) {
                    sender.sendMessage("§c(2) Une erreur vien de se produire veuillez contacter le développeur : NewZ_AZ#7736");
                    data.createFile();
                    return true;
                }

                if (!data.isTempmuted()) {
                    sender.sendMessage("§c" + getPlayerFromName(args[0]) + " n'est pas mute !");
                    return true;
                }

                try {
                    data.setUnTempmuted();
                } catch (IOException e) {
                    e.printStackTrace();
                    sender.sendMessage("§c(2) Une erreur vien de se produire !");
                }
                Bukkit.broadcastMessage("§c(!) " + getPlayerFromName(args[0]) + " a été unmute par " + sender.getName());


            }
        }

        if(cmd.getName().equalsIgnoreCase("kick")){
            if(!sender.hasPermission(APermissionUtils.PERMISSION_KICK) || !sender.hasPermission(APermissionUtils.PERMISSION_ALL) || !sender.isOp()){
                sender.sendMessage(AMessageUtils.MESSAGE_NOPERMISSION.replaceAll("%permission%",APermissionUtils.PERMISSION_KICK));
                return true;
            }

            if(args.length == 0){
                sender.sendMessage("§c/kick <Player> [raison]");
            }

            if(args.length >=1){
                String reason = "";

                for (int i = 1; i < args.length; i++) {
                    reason += args[i] + "";
                }
                reason = reason.trim();

                Player target = Bukkit.getPlayer(args[0]);

                if(target != null){
                    target.kickPlayer(reason.isEmpty() ? "- ArmaCraft -\n\nSanction : Kick \nAuteur : "+ sender.getName()+"\nRaison : Raison non fournie": "- ArmaCraft -\n\nSanction : Kick \nAuteur : "+ sender.getName()+"\nRaison : "+ reason);
                }
            }
        }
        return false;
    }

    public String getPlayerFromName(String name) {
        Player p = Bukkit.getPlayer(name);

        if (p != null) {
            return p.getName();
        } else {

            OfflinePlayer op = Bukkit.getOfflinePlayer(name);

            if (op != null) {
                return op.getName();
            }
        }
        return null;
    }
}
