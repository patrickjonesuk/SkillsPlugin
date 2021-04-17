package me.pjones.skills.commands;

import me.pjones.skills.Skills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterSkillsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Registering all players");
        for (Player plr : Bukkit.getOnlinePlayers()) {
            Skills.INSTANCE.getSkillManager().getPlayerSkill(plr);
        }
        sender.sendMessage(ChatColor.GREEN + "Successfully registered all players");
        return true;
    }
}
