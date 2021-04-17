package me.pjones.skills.commands;

import me.pjones.skills.Skills;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnregisterSkillsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Skills.INSTANCE.getSkillManager().unregisterAll();
        sender.sendMessage(ChatColor.GREEN + "Successfully unregistered all players");
        return true;
    }
}
