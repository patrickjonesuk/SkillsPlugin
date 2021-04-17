package me.pjones.skills.skills;

import me.pjones.skills.Skills;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Magic extends SkillBase {

    private static final float BASE_DAMAGE = 2f;

    public Magic(SkillType skillType, Player plr) {
        super(skillType, plr);
    }

    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile)) return;
        if (!Skills.INSTANCE.getSkillManager().shouldRun((Entity) ((Projectile) event.getDamager()).getShooter(), event.getEntity())) return;
        Player damager = (Player) ((Projectile)event.getDamager()).getShooter();
        if (damager == player && event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
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
