package com.aaomidi.mootags.hooks;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.Tag;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.registry.PlayerRegistry;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class MooTagsPlaceholderExpansion extends PlaceholderExpansion {

    private final MooTags instance;

    @Override
    public String getIdentifier() {
        return "mootags";
    }

    @Override
    public String getPlugin() {
        return instance.getName();
    }

    @Override
    public String getAuthor() {
        return "CowCraft";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("tag")) {
            TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());

            Tag tag = tagPlayer.getTag();

            if (tag == null) {
                return "";
            }

            return tagPlayer.getTag().getTag();
        }

        return null;
    }
}
