package me.pjones.skills.skills;

import me.pjones.skills.Skills;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Combat extends SkillBase {

    private static final float BASE_DAMAGE = 0.5f;

    public Combat(SkillType skillType, Player plr) {
        super(skillType, plr);
    }


    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (!Skills.INSTANCE.getSkillManager().shouldRun(event.getDamager(), event.getEntity())) return;
        Player damager = (Player) event.getDamager();
        if (damager == player && event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            apply(event);
            update(event);
        }
    }

    public void update(EntityDamageByEntityEvent event) {
        progress += event.getDamage();
        levelUp();
    }

    public void apply(EntityDamageByEntityEvent event) {
        event.setDamage(BASE_DAMAGE * event.getDamage() * level * getEffectMultiplier());
    }

}
