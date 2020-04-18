package com.warring.elixirs;

import com.google.common.collect.Lists;
import com.warring.elixirs.commands.ElixirsCommand;
import com.warring.elixirs.commands.managers.CommandHandler;
import com.warring.elixirs.commands.managers.ICommand;
import com.warring.elixirs.listeners.PotionListener;
import com.warring.elixirs.model.Elixir;
import com.warring.elixirs.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    private static Main inst;
    private List<Elixir> elixirList;
    private List<ICommand> commandList;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        inst = this;
        elixirList = Lists.newArrayList();
        commandList = Lists.newArrayList();
        commandList.add(new ElixirsCommand());
        Bukkit.getPluginManager().registerEvents(new PotionListener(), this);
        commandList.forEach(iCommand -> {
            this.getCommand(iCommand.name()).setExecutor(new CommandHandler());
        });
        for (String key : this.getConfig().getConfigurationSection("Elixirs").getKeys(false)) {
            elixirList.add(new Elixir(key));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return inst;
    }

    public List<ICommand> getCommandList() {
        return commandList;
    }

    public void sendMessage(Player p, String location) {
        p.sendMessage(Utils.toColor(this.getConfig().getString("Messages." + location)));
    }

    public void sendMessage(CommandSender p, String location) {
        p.sendMessage(Utils.toColor(this.getConfig().getString("Messages." + location)));
    }


    public void sendMessage(CommandSender p, String location, String replace, String replacement) {
        p.sendMessage(Utils.toColor(this.getConfig().getString("Messages." + location).replaceAll(replace, replacement)));
    }

    public void sendMessage(Player p, String location, String replace, String replacement) {
        p.sendMessage(Utils.toColor(this.getConfig().getString("Messages." + location).replaceAll(replace, replacement)));
    }

    public List<Elixir> getElixirList() {
        return elixirList;
    }
}
