package me.pan.qoolstarapi.files;

import lombok.Getter;
import lombok.Setter;
import me.pan.qoolstarapi.QoolStarAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class playerdataFile {
    @Getter
    public static playerdataFile instance;
    private YamlConfiguration configuration;

    private File file;

    public playerdataFile() {
        this.file = new File(QoolStarAPI.getInstance().getDataFolder(), "playerdata.yml");
        if (!this.file.exists()) {
            QoolStarAPI.getInstance().saveResource("playerdata.yml", false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        instance = this;
    }

    public boolean getBoolean(String path) {
        return (this.configuration.contains(path)) && (this.configuration.getBoolean(path));
    }

    public double getDouble(String path) {
        if (this.configuration.contains(path)) {
            return this.configuration.getDouble(path);
        }
        return 0.0D;
    }

    public File getFile() {
        return this.file;
    }

    public int getInt(String path) {
        if (this.configuration.contains(path)) {
            return this.configuration.getInt(path);
        }
        return 0;
    }

    public String getString(String path) {
        if (this.configuration.contains(path)) {
            return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(path));
        }
        return "null";
    }

    public List<String> getStringList(String path) {
        if (this.configuration.contains(path)) {
            ArrayList<String> strings = new ArrayList<String>();
            for (String string : this.configuration.getStringList(path)) {
                strings.add(ChatColor.translateAlternateColorCodes('&', string));
            }
            return strings;
        }
        return Arrays.asList(new String[]{"null"});
    }

    public void load() {
        this.file = new File(QoolStarAPI.getInstance().getDataFolder(), "playerdata.yml");
        if (!this.file.exists()) {
            QoolStarAPI.getInstance().saveResource("playerdata.yml", false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}