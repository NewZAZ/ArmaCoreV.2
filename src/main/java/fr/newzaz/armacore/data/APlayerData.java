package fr.newzaz.armacore.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APlayerData {

    private final File file = new File("plugins/ArmaCore","sanction.yml");
    private final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    private String player;



    public APlayerData() {
    }

    public APlayerData(String player) {
        this.player = player;
    }

    public boolean exist(){
        return file.exists();
    }

    public void createFile(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTempbanned(String from, String reason, long time) throws IOException {
        config.set(player+".tempban.istempbanned",true);
        config.set(player+".tempban.from", from);
        config.set(player+".tempban.reason", reason);
        config.set(player+".tempban.duration", time + System.currentTimeMillis());
        config.set(player+".tempban.timestamp",new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        config.save(file);
    }

    public void setUnTempbanned() throws IOException {
        config.set(player+".tempban", null);
        config.save(file);
    }

    public String getTempbannedFrom(){
        return config.getString(player+".tempban.from");
    }

    public String getTempbannedReason(){
        return config.getString(player+".tempban.reason");
    }

    public long getTempbanMilliseconds(){
        return config.getLong(player+".tempban.duration");
    }

    public String getTempbanTimestamp(){
        return config.getString(player+".tempban.timestamp");
    }

    public boolean isTempbanned(){
        return config.getBoolean(player+".tempban.istempbanned");
    }

    public void setTempmuted(String from, String reason, long time) throws IOException {
        config.set(player+".tempmute.istempbanned",true);
        config.set(player+".tempmute.from", from);
        config.set(player+".tempmute.reason", reason);
        config.set(player+".tempmute.duration", time + System.currentTimeMillis());
        config.set(player+".tempmute.timestamp",new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        config.save(file);
    }

    public void setUnTempmuted() throws IOException {
        config.set(player+".tempmute", null);
        config.save(file);
    }

    public String getTempmutedFrom(){
        return config.getString(player+".tempmute.from");
    }

    public String getTempmutedReason(){
        return config.getString(player+".tempmute.reason");
    }

    public long getTempmuteMilliseconds(){
        return config.getLong(player+".tempmute.duration");
    }

    public String getTempmuteTimestamp(){
        return config.getString(player+".tempmute.timestamp");
    }

    public boolean isTempmuted(){
        return config.getBoolean(player+".tempmute.istempbanned");
    }
}
