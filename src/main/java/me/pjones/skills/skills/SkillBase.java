package me.pjones.skills.skills;

import me.pjones.skills.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class SkillBase implements Listener {

    protected SkillType type;
    protected Player player;
    protected int level = 1;
    protected int progress = 0;

    public SkillBase(SkillType skillType, Player plr) {
        type = skillType;
        player = plr;
    }

    protected double getEffectMultiplier() {
        return ConfigManager.getConfig().getDouble("skills." + type.getName() + ".multipliers.effect");
    }



    public SkillType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getProgress() {
        return progress;
    }

    public boolean levelUp() {
        if (progress > getRequiredProgress()) {
            progress = 0;
            player.sendMessage(ChatColor.GREEN + "You just levelled up " + ChatColor.GOLD + type.getName() + ChatColor.GREEN +
                    " to level " + ChatColor.BOLD + ++level);
            return true;
        }
        return false;
    }

    public int getRequiredProgress() {
        ConfigurationSection config = ConfigManager.getConfig().getConfigurationSection("skills." + type.getName());
        double exponentialRate = config.getDouble("multipliers.rate");
        double baseXp = config.getDouble("multipliers.baseXp");
        double exponentialBase = config.getDouble("multipliers.exponentialBase");
        return (int) (baseXp * Math.pow(exponentialBase, exponentialRate * level));
    }


}
