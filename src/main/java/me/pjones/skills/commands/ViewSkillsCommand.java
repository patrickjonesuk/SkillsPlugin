package me.pjones.skills.commands;

import me.pjones.skills.Skills;
import me.pjones.skills.skills.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class ViewSkillsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player admin = (Player) sender;
        Inventory inv = Bukkit.createInventory(admin, 54, "Player overview");

        int i = 0;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (i > 54) break;

            PlayerSkill playerSkill = Skills.INSTANCE.getSkillManager().getPlayerSkill(player, false);
            if (playerSkill == null) continue;

            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwningPlayer(player);
            meta.setDisplayName(player.getName());
            ArrayList<String> lore = new ArrayList<>();
            for (SkillType skillType : SkillType.values()) {
                SkillBase skillInfo = playerSkill.skills.get(skillType);
                if (skillInfo == null) continue;
                lore.add(ChatColor.GRAY + skillType.getName() + ": " + ChatColor.WHITE + "Level " + skillInfo.getLevel()
                        + "; " + skillInfo.getProgress() + "/" + skillInfo.getRequiredProgress());
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(i++, item);
        }

        admin.openInventory(inv);
        return true;
    }
}
