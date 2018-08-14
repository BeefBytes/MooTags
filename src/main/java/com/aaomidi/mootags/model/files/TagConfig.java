package com.aaomidi.mootags.model.files;

import com.aaomidi.mootags.model.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by amir on 2016-01-08.
 */
@RequiredArgsConstructor
public class TagConfig {
    @Getter
    private final String prefix;
    @Getter
    private final String inventoryName;
    @Getter
    private final String defaultPlaceholder;
    @Getter
    private final List<Tag> tags;

    private transient Set<String> placeholders;

    public Set<String> getAllPlaceholders() {
        if (placeholders != null) return placeholders;

        placeholders = new HashSet<>();
        tags.stream().forEach(t -> placeholders.add(t.getPlaceholder()));
        return placeholders;
    }

    public String cleanAllPlaceholders(String string) {
        for (String s : getAllPlaceholders()) {
            string = string.replace(s, "");
        }
        return string;
    }
}
