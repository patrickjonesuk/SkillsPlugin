package me.pjones.skills.managers;

import me.pjones.skills.skills.PlayerSkill;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SkillManager {

    private HashMap<Player, PlayerSkill> skillMap = new HashMap<>();

    public PlayerSkill getPlayerSkill(Player player) {
        return getPlayerSkill(player, true);
    }

    public PlayerSkill getPlayerSkill(Player player, boolean create) {
        if (skillMap.containsKey(player)) return skillMap.get(player);
        if (!create) return null;
        PlayerSkill playerSkill = new PlayerSkill(player);
        skillMap.put(player, playerSkill);
        return playerSkill;
    }

    public boolean shouldRun(Entity player, Entity target) {
        ConfigurationSection settings = ConfigManager.getConfig().getConfigurationSection("settings");
        return player instanceof Player && (target instanceof Player && settings.getBoolean("pvp") || settings.getBoolean("pve"));
    }

    public void unregisterAll() {
        for (PlayerSkill skill : skillMap.values()) skill.unregisterSkills();
        skillMap.clear();
    }

}
