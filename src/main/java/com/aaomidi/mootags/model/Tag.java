package com.aaomidi.mootags.model;

import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.util.StringManager;
import io.mazenmc.menuapi.items.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.logging.Level;

/**
 * Created by amir on 2016-01-08.
 */
@AllArgsConstructor
public class Tag implements Item {
    @Getter
    private final String id;
    @Getter
    private final String permission;
    @Getter
    private final String tag;
    @Getter
    private final List<String> lore;
    @Getter
    @Setter
    private String placeholder;

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG, 1);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringManager.colorize(tag));
        if (lore != null) {
            itemMeta.setLore(StringManager.colorizeList(lore));
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public ItemStack stack() {
        return toItemStack();
    }

    @Override
    public void act(Player player, ClickType clickType) {
        TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());

        if (tagPlayer == null) {
            StringManager.log(Level.SEVERE, "%s player not found!", player.getName());
            return;
        }

        tagPlayer.apply(this);
        player.closeInventory();
    }

    public String apply(String s) {
        return s.replace(getPlaceholder(), StringManager.colorize(tag));
    }

}
