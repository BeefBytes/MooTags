package com.aaomidi.mootags.model;

import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.util.StringManager;
import io.mazenmc.menuapi.items.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by amir on 2016-01-08.
 */
public class RemoveTagItem implements Item {
    private ItemStack cache;

    @Override
    public ItemStack stack() {
        if (cache != null) {
            return cache;
        }

        cache = new ItemStack(Material.BARRIER);

        ItemMeta itemMeta = cache.getItemMeta();
        itemMeta.setDisplayName(StringManager.colorize("&cRemove Tag"));

        cache.setItemMeta(itemMeta);

        return cache;
    }

    @Override
    public void act(Player player, ClickType clickType) {
        TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());
        tagPlayer.setTagID("");

        player.closeInventory();
    }
}
