package com.warring.elixirs.commands;

import com.warring.elixirs.Main;
import com.warring.elixirs.commands.managers.ICommand;
import com.warring.elixirs.model.Elixir;
import com.warring.elixirs.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ElixirsCommand implements ICommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        switch (args.length) { // /elixirs give {player} {elixir} {amount}
            case 4:
                if (args[0].equalsIgnoreCase("give")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        ConfigurationSection sec = Main.getInstance().getConfig().getConfigurationSection("Elixirs." + args[2]);
                        if (sec != null) {
                            String type = args[2];
                            ItemStack item = new Elixir(args[2]).getItem();
                            int amount;
                            try {
                                amount = Integer.parseInt(args[3]);
                            } catch (NumberFormatException ex) {
                                Main.getInstance().sendMessage(sender, "InvalidInteger", "%int%", args[3]);
                                return;
                            }
                            item.setAmount(amount);
                            target.getInventory().addItem(item);
                            String targetMessage = Main.getInstance().getConfig().getString("Messages.RecievedItem");
                            String senderMessage = Main.getInstance().getConfig().getString("Messages.GivenItem");
                            target.sendMessage(Utils.toColor(targetMessage.replaceAll("%amount%", amount + "").replaceAll("%type%", type)));
                            sender.sendMessage(Utils.toColor(senderMessage.replaceAll("%player%", target.getName()).replaceAll("%amount%", amount + "").replaceAll("%type%", type)));
                            return;
                        } else {
                            Main.getInstance().sendMessage(sender, "InvalidElixir", "%type%", args[2]);
                        }
                    } else {
                        Main.getInstance().sendMessage(sender, "InvalidPlayer", "%player%", args[1]);
                    }
                }
                break;
        }
    }

    @Override
    public String name() {
        return "elixirs";
    }

    @Override
    public String permission() {
        return "warringelixirs.admin";
    }
}
