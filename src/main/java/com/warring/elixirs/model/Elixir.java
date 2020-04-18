package com.warring.elixirs.model;

import com.warring.elixirs.Main;
import com.warring.elixirs.utils.Utils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Elixir {

    private ItemStack item;
    private List<String> effects;

    public Elixir(String name) {
        ConfigurationSection sec = Main.getInstance().getConfig().getConfigurationSection("Elixirs." + name);
        ItemStack item1 = Utils.getConfigItem("Elixirs." + name + ".Item");
        ItemMeta meta = item1.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_POTION_EFFECTS});
        item1.setItemMeta(meta);
        item = item1;
        effects = sec.getStringList("Effects");
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getEffects() {
        return effects;
    }

    public void giveEffects(Player p) {
        for (String effect : effects) {
            String[] args = effect.split(";");
            PotionEffectType eff = PotionEffectType.getByName(args[0]);
            int dur = Integer.parseInt(args[1]);
            int val = Integer.parseInt(args[2]);
            if (p.hasPotionEffect(eff)) {
                p.removePotionEffect(eff);
            }
            p.addPotionEffect(new PotionEffect(eff, dur * 20, val -1));
        }
    }
}
