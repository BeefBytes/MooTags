package com.aaomidi.mootags.engine;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.RemoveTagItem;
import com.aaomidi.mootags.model.Tag;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.util.StringManager;
import io.mazenmc.menuapi.MenuFactory;
import io.mazenmc.menuapi.menu.ScrollingMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by amir on 2016-01-08.
 */
public class GUICreator {
    private final MooTags instance;
    private final RemoveTagItem removeTagItem;
    private ItemStack panel = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0, (byte) 7);

    public GUICreator(MooTags instance) {
        this.instance = instance;
        removeTagItem = new RemoveTagItem();

        ItemMeta meta = panel.getItemMeta();
        meta.setDisplayName(StringManager.colorize("&r"));
        panel.setItemMeta(meta);
    }

    public ScrollingMenu createInventoryForPlayer(Player player) {
        ScrollingMenu scrollingMenu = MenuFactory.createScrollingMenu(instance.getDataManager().getConfigFile().getInventoryName(), removeTagItem);

        scrollingMenu.setPanel(panel);
        TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());
        List<Tag> tags = tagPlayer.getApplicableTags();

        int i = 0;

        for (Tag tag : tags) {
            scrollingMenu.setItem(i++, tag);
        }

        scrollingMenu.flush();
        return scrollingMenu;
    }
}
