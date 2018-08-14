package com.aaomidi.mootags.registry;

import com.aaomidi.mootags.model.TagPlayer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by amir on 2016-01-08.
 */
public class PlayerRegistry {
    @Getter
    private static final Map<UUID, TagPlayer> uuidTagMap = new HashMap<>();

    public static boolean addPlayer(TagPlayer tagPlayer) {
        uuidTagMap.put(tagPlayer.getUuid(), tagPlayer);
        return true;
    }

    public static TagPlayer getPlayer(UUID uuid) {
        return uuidTagMap.get(uuid);
    }

    public static void reset() {
        uuidTagMap.clear();
    }


}
