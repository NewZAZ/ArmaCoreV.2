package fr.newzaz.armacore.commands;

import fr.newzaz.armacore.utils.APermissionUtils;
import fr.newzaz.armacore.utils.IntegerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AMoneyCommand implements CommandExecutor {
    File file = new File("plugins/ArmaCore", "money.yml");
    FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cAttention tu doit être un joueur pour faire cette commande");
            return false;
        }
        Player p = (Player) sender;


        if (cmd.getName().equalsIgnoreCase("money")) {
            if (args.length == 0) {
                try {
                    p.sendMessage("§aBalance : " + getMoney(p.getName()) + "€");
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                return false;
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    p.sendMessage("§cArguments: /money [Player]");
                    return false;
                }
                if (config.get(target.getName() + ".balance") == null) {
                    config.set(target.getName() + ".balance", 0);
                    try {
                        config.save(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
                try {
                    p.sendMessage("§a" + target.getName() + " Balance's : " + getMoney(target.getName()) + "€");
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
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
                            setMoney(p.getName(), Integer.parseInt(args[0]));
                            p.sendMessage("§aTu vien de te donner " + Integer.parseInt(args[0]) + "€");
                        } catch (IOException | InvalidConfigurationException e) {
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

                    if (IntegerUtils.isInteger(args[1])) {
                        try {
                            setMoney(target.getName(), Integer.parseInt(args[1]));
                            target.sendMessage("§aUn §cAdministrateur §avien de te donner " + Integer.parseInt(args[1]) + "€");
                            p.sendMessage("§aTu vien de donner " + Integer.parseInt(args[1]) + "€ à " + target.getName());
                        } catch (IOException | InvalidConfigurationException e) {
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

                if (IntegerUtils.isInteger(args[1])) {
                    try {
                        addMoney(target.getName(), Integer.parseInt(args[1]));
                        removeMoney(p.getName(), Integer.parseInt(args[1]));
                        target.sendMessage("§aTu vien de recevoir " + Integer.parseInt(args[1]) + "€ de " + p.getName());
                        p.sendMessage("§cTu vien d'envoyer " + Integer.parseInt(args[1]) + "€ à " + target.getName());
                    } catch (IOException | InvalidConfigurationException e) {
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
                    try {
                        resetMoney(target.getName());
                    } catch (IOException | InvalidConfigurationException e) {
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


    public void setMoney(String player, int amount) throws IOException, InvalidConfigurationException {
        config.set(player + ".balance", amount);
        config.save(file);
        config.load(file);
    }

    public void addMoney(String player, int amount) throws IOException, InvalidConfigurationException {
        config.set(player + ".balance", getMoney(player) + amount);
        config.save(file);
        config.load(file);
    }

    public void removeMoney(String player, int amount) throws IOException, InvalidConfigurationException {
        config.set(player + ".balance", getMoney(player) - amount);
        config.save(file);
        config.load(file);
    }

    public void resetMoney(String player) throws IOException, InvalidConfigurationException {
        config.set(player + ".balance", 0);
        config.save(file);
        config.load(file);
    }

    public int getMoney(String player) throws IOException, InvalidConfigurationException {
        if (config.get(player + ".balance") != null) {
            config.load(file);
            return config.getInt(player + ".balance");
        }
        return 0;
    }

}
