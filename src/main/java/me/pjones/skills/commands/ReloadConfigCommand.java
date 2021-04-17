package me.pjones.skills.commands;

import me.pjones.skills.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigManager.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded config");
        return true;
    }
}
