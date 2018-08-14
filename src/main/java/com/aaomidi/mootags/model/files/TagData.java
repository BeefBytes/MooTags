package com.aaomidi.mootags.model.files;

import com.aaomidi.mootags.model.TagPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by amir on 2016-01-08.
 */
@RequiredArgsConstructor
public class TagData {
    @Getter
    private final List<TagPlayer> tagPlayers;

    public boolean getChangesMade() {
        for (TagPlayer tagPlayer : tagPlayers) {
            if (tagPlayer.isChangesMade())
                return true;
        }
        return false;
    }

    public void setChangesSaved() {
        for (TagPlayer tagPlayer : tagPlayers) {
            tagPlayer.setChangesMade(false);
        }
    }
}
