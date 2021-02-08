package fr.newzaz.armacore;

import fr.newzaz.armacore.commands.*;
import fr.newzaz.armacore.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("mod").setExecutor(new AModerationCommand());
        getCommand("money").setExecutor(new AMoneyCommand());
        getCommand("gmoney").setExecutor(new AMoneyCommand());
        getCommand("pay").setExecutor(new AMoneyCommand());
        getCommand("rmoney").setExecutor(new AMoneyCommand());
        getCommand("tempban").setExecutor(new ASanctionCommand());
        getCommand("unban").setExecutor(new ASanctionCommand());
        getCommand("tempmute").setExecutor(new ASanctionCommand());
        getCommand("unmute").setExecutor(new ASanctionCommand());
        getCommand("kick").setExecutor(new ASanctionCommand());
        getCommand("vanish").setExecutor(new AModerationCommand());
        getCommand("fly").setExecutor(new AFlyCommand());
        getCommand("gmc").setExecutor(new AGamemodeCommand());
        getCommand("gms").setExecutor(new AGamemodeCommand());
        getCommand("gmsp").setExecutor(new AGamemodeCommand());

    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new AAsyncPlayerChatEvent(),this);
        pm.registerEvents(new ABlockBreakEvent(this), this);
        pm.registerEvents(new ABlockExplodeEvent(),this);
        pm.registerEvents(new ABlockPlaceEvent(), this);
        pm.registerEvents(new AEntityChangeBlockEvent(), this);
        pm.registerEvents(new AExplosionPrimeEvent(), this);
        pm.registerEvents(new APlayerDropItemEvent(),this);
        pm.registerEvents(new APlayerJoinEvent(),this);
        pm.registerEvents(new APlayerInteractAtEntityEvent(this),this);
        pm.registerEvents(new APlayerQuitEvent(),this);
        pm.registerEvents(new AInventoryClickEvent(),this);
        pm.registerEvents(new APlayerLoginEvent(),this);
        pm.registerEvents(new APlayerMoveEvent(),this);
    }


}
