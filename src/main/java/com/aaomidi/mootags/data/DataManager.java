package com.aaomidi.mootags.data;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.Tag;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.model.files.TagConfig;
import com.aaomidi.mootags.model.files.TagData;
import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.registry.TagRegistry;
import com.aaomidi.mootags.util.StringManager;
import com.google.gson.Gson;
import lombok.Getter;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by amir on 2016-01-08.
 */
public class DataManager {
    private final MooTags instance;
    @Getter
    private TagConfig configFile;
    @Getter
    private TagData tagData;

    public DataManager(MooTags instance) {
        this.instance = instance;
        this.readDataFiles();
        this.startRunnable();
    }

    private void startRunnable() {
        Gson gson = new Gson();
        File file = new File(instance.getDataFolder(), "data.json");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!tagData.getChangesMade()) {
                    return;
                }
                String json = gson.toJson(tagData, TagData.class);
                writeToFile(file, json);
                tagData.setChangesSaved();
            }
        }.runTaskTimerAsynchronously(instance, 100L, 600L);
    }

    public void readDataFiles() {
        Gson gson = new Gson();
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }

        File file1 = new File(instance.getDataFolder(), "config.json");
        File file2 = new File(instance.getDataFolder(), "data.json");

        if (!file1.exists()) {
            createConfigFile(file1);
        }

        if (!file2.exists()) {
            createDataFile(file2);
        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file1);
            configFile = gson.fromJson(fileReader, TagConfig.class);
            fileReader = new FileReader(file2);
            tagData = gson.fromJson(fileReader, TagData.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (Tag tag : configFile.getTags()) {
            if (tag.getPlaceholder() == null || tag.getPlaceholder().equals("")) {
                tag.setPlaceholder(configFile.getDefaultPlaceholder());
            }
            TagRegistry.addTag(tag);
        }

        tagData.getTagPlayers().forEach(PlayerRegistry::addPlayer);

        StringManager.setPrefix(configFile.getPrefix());
    }

    public void createDataFile(File file) {
        Gson gson = new Gson();
        TagPlayer tagPlayer1 = new TagPlayer(UUID.randomUUID(), "DanceWithSomebody");
        TagPlayer tagPlayer2 = new TagPlayer(UUID.randomUUID(), "WhereHaveYouBeen");

        List<TagPlayer> tagPlayers = new ArrayList<>();
        tagPlayers.add(tagPlayer1);
        tagPlayers.add(tagPlayer2);

        TagData tagData = new TagData(tagPlayers);


        String json = gson.toJson(tagData, TagData.class);
        writeToFile(file, json);
    }

    private void createConfigFile(File file) {
        Gson gson = new Gson();
        List<Tag> tags = new ArrayList<>();


        for (int i = 0; i < 300; i++) {
            tags.add(new Tag(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5), null, RandomStringUtils.randomAlphabetic(5)));
        }
        Tag tag1 = new Tag("DanceWithSomebody", "random.permission", "Le tag", null, "[PlaceHolder-1]");

        List<String> lore = new ArrayList<>();
        lore.add("Lore line 1");
        lore.add("Lore line 2");
        Tag tag2 = new Tag("WhereHaveYouBeen", "another.permission", "Tag2", lore, null);

        tags.add(tag1);
        tags.add(tag2);

        TagConfig tagConfig = new TagConfig("Prefix", "Tags!", "Default", tags);

        String json = gson.toJson(tagConfig, TagConfig.class);
        writeToFile(file, json);
    }

    private void writeToFile(File file, String json) {
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
