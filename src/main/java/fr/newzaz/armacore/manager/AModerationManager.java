package fr.newzaz.armacore.manager;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AModerationManager {

    private final ArrayList<UUID> moderationUUID;
    private final ArrayList<UUID> freezeUUID;
    private final ArrayList<UUID> playerUUIDVanish;
    private final HashMap<UUID, ItemStack[]> inventory;

    public AModerationManager() {
        moderationUUID = new ArrayList<>();
        freezeUUID = new ArrayList<>();
        playerUUIDVanish = new ArrayList<>();
        inventory = new HashMap<>();
    }

    public boolean playerIsInMod(UUID uuid){
        return moderationUUID.contains(uuid);
    }

    public void addPlayerInMod(UUID uuid){
        this.moderationUUID.add(uuid);
    }

    public void removePlayerInMod(UUID uuid){
        this.moderationUUID.remove(uuid);
    }

    public boolean playerIsFreeze(UUID uuid){
        return this.freezeUUID.contains(uuid);
    }

    public void addPlayerInFreeze(UUID uuid){
        this.freezeUUID.add(uuid);
    }

    public void removePlayerInFreeze(UUID uuid){
        this.freezeUUID.remove(uuid);
    }

    public boolean playerIsVanished(UUID uuid){
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
