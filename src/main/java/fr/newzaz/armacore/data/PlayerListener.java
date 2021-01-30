package fr.newzaz.armacore.data;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerListener {

    public ArrayList<UUID> ModerationUUID = new ArrayList<>();
    public ArrayList<UUID> FreezeUUID = new ArrayList<>();
    public ArrayList<UUID> playerUUIDVanish = new ArrayList<>();
    public HashMap<String, ItemStack[]> inventory = new HashMap<>();

    public ArrayList<UUID> getModerationUUID() {
        return ModerationUUID;
    }

    public ArrayList<UUID> getFreezeUUID() {
        return FreezeUUID;
    }

    public ArrayList<UUID> getPlayerUUIDVanish() {
        return playerUUIDVanish;
    }

    public HashMap<String, ItemStack[]> getInventory() {
        return inventory;
    }
}
