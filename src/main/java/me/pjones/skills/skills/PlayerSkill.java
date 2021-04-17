package me.pjones.skills.skills;

import me.pjones.skills.Skills;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.HashMap;
import java.util.Map;

public class PlayerSkill {

    public HashMap<SkillType, SkillBase> skills = new HashMap<>();
    private Player player;

    public PlayerSkill(Player player) {
        this.player = player;
        registerSkills();
    }

    private void registerSkills() {

        for (SkillType skillType : SkillType.values()) {
            try {
                ConfigurationSection skillConfig = Skills.INSTANCE.getConfig().getConfigurationSection("skills." + skillType.getName());
                if (skillConfig.getBoolean("enabled")) {
                    SkillBase instance = skillType.getImplementation()
                            .getDeclaredConstructor(SkillType.class, Player.class)
                            .newInstance(skillType, player);
                    Skills.INSTANCE.registerEvents(instance);
                    skills.put(skillType, instance);
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterSkills() {
        for (Map.Entry<SkillType, SkillBase> entry : skills.entrySet()) {
            HandlerList.unregisterAll(entry.getValue());
        }
    }

}
