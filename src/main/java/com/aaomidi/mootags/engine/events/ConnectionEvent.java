package com.aaomidi.mootags.engine.events;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.registry.PlayerRegistry;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by amir on 2016-01-08.
 */
@RequiredArgsConstructor
public class ConnectionEvent implements Listener {
    private final MooTags instance;

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        TagPlayer tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());
        if (tagPlayer == null) {
            tagPlayer = new TagPlayer(player.getUniqueId(), "");
            tagPlayer.setChangesMade(true);
            instance.getDataManager().getTagData().getTagPlayers().add(tagPlayer);
            PlayerRegistry.addPlayer(tagPlayer);
        }
    }
}
