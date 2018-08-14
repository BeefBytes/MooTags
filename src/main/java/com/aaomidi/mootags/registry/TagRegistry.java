package com.aaomidi.mootags.registry;

import com.aaomidi.mootags.model.Tag;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amir on 2016-01-08.
 */
public class TagRegistry {
    @Getter
    private static final Map<String, Tag> tags = new HashMap<>();

    public static void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }

    public static Tag getTag(String id) {
        return tags.get(id);
    }

    public static void reset() {
        tags.clear();
    }


}
