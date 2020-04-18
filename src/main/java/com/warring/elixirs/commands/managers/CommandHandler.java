package com.warring.elixirs.commands.managers;

import com.warring.elixirs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Main.getInstance().getCommandList().forEach(iCommand -> {
            if (command.getName().equalsIgnoreCase(iCommand.name())) {
                if (sender.hasPermission(iCommand.permission()) || iCommand.permission() == null) {
                    iCommand.onCommand(sender, args);
                } else {
                    Main.getInstance().sendMessage(sender, "NoPermission");
                }
            }
        });
        return false;
    }
}
