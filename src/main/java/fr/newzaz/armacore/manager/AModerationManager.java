package fr.newzaz.armacore.manager;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AModerationManager {

    private final ArrayList<UUID> ModerationUUID;
    private final ArrayList<UUID> FreezeUUID;
    private final ArrayList<UUID> playerUUIDVanish;
    private final HashMap<UUID, ItemStack[]> inventory;

    public AModerationManager() {
        ModerationUUID = new ArrayList<>();
        FreezeUUID = new ArrayList<>();
        playerUUIDVanish = new ArrayList<>();
        inventory = new HashMap<>();
    }

    public boolean PlayerIsMod(UUID uuid){
        return ModerationUUID.contains(uuid);
    }

    public void addPlayerInMod(UUID uuid){
        this.ModerationUUID.add(uuid);
    }

    public void removePlayerInMod(UUID uuid){
        this.ModerationUUID.remove(uuid);
    }

    public boolean PlayerIsFreeze(UUID uuid){
        return this.FreezeUUID.contains(uuid);
    }

    public void addPlayerInFreeze(UUID uuid){
        this.FreezeUUID.add(uuid);
    }

    public void removePlayerInFreeze(UUID uuid){
        this.FreezeUUID.remove(uuid);
    }

    public boolean PlayerIsVanished(UUID uuid){
        return this.playerUUIDVanish.contains(uuid);
    }

    public void addPlayerInVanish(UUID uuid){
        this.playerUUIDVanish.add(uuid);
    }

    public void removePlayerInVanish(UUID uuid){
        this.playerUUIDVanish.remove(uuid);
    }

    public ItemStack[] getInventory(UUID uuid){
        return this.inventory.get(uuid);
    }

    public void setInventoryContents(ItemStack[] i,UUID uuid){
        this.inventory.put(uuid,i);
    }
}
