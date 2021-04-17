package me.pjones.skills;

import me.pjones.skills.commands.RegisterSkillsCommand;
import me.pjones.skills.commands.ReloadConfigCommand;
import me.pjones.skills.commands.UnregisterSkillsCommand;
import me.pjones.skills.commands.ViewSkillsCommand;
import me.pjones.skills.managers.SkillManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Skills extends JavaPlugin {

    public static Skills INSTANCE;
    private SkillManager skillManager;

    public SkillManager getSkillManager() {
        return skillManager;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        skillManager = new SkillManager();
        getCommand("registerskills").setExecutor(new RegisterSkillsCommand());
        getCommand("unregisterskills").setExecutor(new UnregisterSkillsCommand());
        getCommand("viewskills").setExecutor(new ViewSkillsCommand());
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand());
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
