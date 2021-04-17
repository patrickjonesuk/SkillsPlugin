package me.pjones.skills.skills;

import me.pjones.skills.Skills;
import me.pjones.skills.managers.ConfigManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Endurance extends SkillBase {

    private static final int BASE_DAMAGE = 2;

    public Endurance(SkillType skillType, Player plr) {
        super(skillType, plr);
    }


    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (!Skills.INSTANCE.getSkillManager().shouldRun(event.getEntity(), event.getDamager())) return;
        Player damaged = (Player) event.getEntity();
        if (damaged == player) {
            apply(event);
            update(event);
        }
    }

    public void update(EntityDamageByEntityEvent event) {
        progress += event.getDamage();
        if (levelUp()) {
            if (level % ConfigManager.getConfig().getInt("skills.Endurance.abilities.heart") == 0) {
                AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                maxHealth.setBaseValue(maxHealth.getBaseValue()+2);
            }
        }
    }

    public void apply(EntityDamageByEntityEvent event) {
        event.setDamage(BASE_DAMAGE * event.getDamage() / (level * getEffectMultiplier()));
    }

}
