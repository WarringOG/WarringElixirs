package com.warring.elixirs.utils;

import com.warring.elixirs.Main;
import com.warring.elixirs.model.PotionType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

import java.util.List;

public class Utils {

    public static String toColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack getConfigItem(String path) {
        ConfigurationSection sec = Main.getInstance().getConfig().getConfigurationSection(path);
        PotionType mat = PotionType.fromString(sec.getString("Type"));
        String data = sec.getString("Potion");
        String name = sec.getString("Name");
        List<String> lore = sec.getStringList("Lore");
        Potion potion = new Potion(org.bukkit.potion.PotionType.valueOf(data));
        if (mat == PotionType.SPLASH) {
            ItemBuilder builder = new ItemBuilder(potion.splash().toItemStack(1));
            builder.setName(name);
            builder.setLore(lore);
            return builder.getStack();
        }
        ItemBuilder builder = new ItemBuilder(potion.toItemStack(1));
        builder.setName(name);
        builder.setLore(lore);
        return builder.getStack();
    }


}
