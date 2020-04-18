package com.warring.elixirs.commands.managers;

import org.bukkit.command.CommandSender;

public interface ICommand {

    void onCommand(CommandSender sender, String[] args);

    String name();

    String permission();
}
