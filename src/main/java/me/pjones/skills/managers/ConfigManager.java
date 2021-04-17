package me.pjones.skills.managers;

import me.pjones.skills.Skills;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config = null;

    public static FileConfiguration getConfig() {
        return config != null ? config : Skills.INSTANCE.getConfig();
    }
    public static void reloadConfig() {
        config = null;
    }

}
