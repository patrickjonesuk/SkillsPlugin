package me.pjones.skills.skills;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Agility extends SkillBase {

    private static final double BASE_SPEED = 0.5f;
    private float walkSpeed = 0.2f;
    private boolean wasSprinting = false;
    private int bonusSpeedPotionAmplifier = 0;

    public Agility(SkillType skillType, Player plr) {
        super(skillType, plr);
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        double dist = Math.abs(event.getTo().distance(event.getFrom())*10);
        progress += dist;
        if (!event.getPlayer().isSprinting()) {
            if (wasSprinting) event.getPlayer().setWalkSpeed(Math.min(walkSpeed, 1));
            else walkSpeed = event.getPlayer().getWalkSpeed();
            wasSprinting = false;
        }
        if (event.getPlayer().isSprinting()) {
            event.getPlayer().setWalkSpeed(Math.min(Math.max((float) (walkSpeed * BASE_SPEED * level * getEffectMultiplier()), 0.2f), 1));
            wasSprinting = true;
        }

        if (levelUp()) {
            if (level % 10 == 0) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, bonusSpeedPotionAmplifier++));
            }
        }

    }
}
