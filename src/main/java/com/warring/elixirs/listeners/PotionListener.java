package com.warring.elixirs.listeners;

import com.warring.elixirs.Main;
import com.warring.elixirs.model.Elixir;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PotionListener implements Listener {

    @EventHandler
    public void drinkElixir(PlayerItemConsumeEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.POTION) {
                if (e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().hasLore()) {
                    boolean elixir = false;
                    Elixir elix = null;
                    for (Elixir elx : Main.getInstance().getElixirList()) {
                        if (elx.getItem().isSimilar(e.getItem())) {
                            elixir = true;
                            elix = elx;
                            break;
                        }
                    }
                    if (elixir) {
                        e.setCancelled(true);
                        if (e.getPlayer().getItemInHand().getAmount() > 1) {
                            e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                        } else {
                            e.getPlayer().setItemInHand(null);
                        }
                        elix.giveEffects(e.getPlayer());
                        Main.getInstance().sendMessage(e.getPlayer(), "DrunkElixir");
                    }
                }
            }
        }
    }

    @EventHandler
    public void splashEvent(PotionSplashEvent e) {
        ItemStack item = e.getPotion().getItem();
        if (item != null) {
            if (item.getType() == Material.POTION) {
                if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
                    boolean elixir = false;
                    Elixir elix = null;
                    for (Elixir elx : Main.getInstance().getElixirList()) {
                        if (elx.getItem().isSimilar(item)) {
                            elixir = true;
                            elix = elx;
                            break;
                        }
                    }
                    if (elixir) {
                        e.setCancelled(true);
                        if (e.getPotion().getItem().getAmount() > 1) {
                            e.getPotion().getItem().setAmount(e.getPotion().getItem().getAmount() - 1);
                        } else {
                            e.getPotion().getItem().setType(Material.AIR);
                        }
                        for (LivingEntity effected : e.getAffectedEntities()) {
                            if (effected instanceof Player) {
                                elix.giveEffects((Player) effected);
                                Main.getInstance().sendMessage((Player) effected, "SplashedElixir");
                            }
                        }
                    }
                }
            }
        }
    }
}
