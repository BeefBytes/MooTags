package com.aaomidi.mootags.model;

import com.aaomidi.mootags.registry.TagRegistry;
import com.aaomidi.mootags.util.StringManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by amir on 2016-01-08.
 */
@RequiredArgsConstructor
public class TagPlayer {
    @Getter
    private final UUID uuid;
    @Getter
    @NonNull
    private String tagID;
    @Getter
    @Setter
    private transient boolean changesMade = false;


    public void setTagID(String tagID) {
        this.tagID = tagID;
        this.changesMade = true;

        Player player = getPlayer();
        if (player != null) {
            Tag tag = TagRegistry.getTag(tagID);
            if (tag == null) {
                StringManager.sendMessage(player, "&dYour tag was removed!");
            } else {
                StringManager.sendMessage(player, "&dYour new tag is: &r" + tag.getTag());
            }
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public Tag getTag() {

        return TagRegistry.getTag(tagID);
    }

    public void apply(Tag tag) {
        this.setTagID(tag.getId());
    }

    public List<Tag> getApplicableTags() {
        Player player = Bukkit.getPlayer(uuid);
        LinkedList<Tag> list = new LinkedList<>();

        if (player == null)
            return list;

        for (Tag t : TagRegistry.getTags().values()) {
            if (!player.hasPermission(t.getPermission()))
                continue;
            list.add(t);
        }

        return list;
    }

    public String applyTags(String s) {
        Tag tag = getTag();
        if (tag == null)
            return s;
        return tag.apply(s);
    }
}
