package com.aaomidi.mootags.engine.events;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.registry.PlayerRegistry;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by amir on 2016-01-08.
 */
@RequiredArgsConstructor
public class ChatEvent implements Listener {
    private final MooTags instance;

    @EventHandler(priority = EventPriority.HIGHEST)

    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (instance.isPlaceholderAPIEnabled()) {
            return;
        }
        Player player = event.getPlayer();
        String format = event.getFormat();

        TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());

        format = tagPlayer.applyTags(format);

        format = instance.getDataManager().getConfigFile().cleanAllPlaceholders(format);

        event.setFormat(format);
    }

}
