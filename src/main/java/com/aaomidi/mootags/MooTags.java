package com.aaomidi.mootags;

import com.aaomidi.mootags.data.DataManager;
import com.aaomidi.mootags.engine.CommandHandler;
import com.aaomidi.mootags.engine.GUICreator;
import com.aaomidi.mootags.engine.events.ChatEvent;
import com.aaomidi.mootags.engine.events.ConnectionEvent;
import com.aaomidi.mootags.hooks.MooTagsPlaceholderExpansion;
import com.aaomidi.mootags.util.StringManager;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Created by amir on 2016-01-08.
 */
public class MooTags extends JavaPlugin {
    @Getter
    private DataManager dataManager;
    @Getter
    private CommandHandler commandHandler;
    @Getter
    private GUICreator guiCreator;
    @Getter
    private boolean placeholderAPIEnabled = false;


    @Override
    public void onEnable() {
        StringManager.setLogger(this.getLogger());
        dataManager = new DataManager(this);
        guiCreator = new GUICreator(this);
        commandHandler = new CommandHandler(this);
        commandHandler.registerCommands();

        this.setupEvents();
        this.setupDeluxeTags();
    }


    private void setupEvents() {
        registerEvent(new ChatEvent(this));
        registerEvent(new ConnectionEvent(this));
    }

    private void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    private void setupDeluxeTags() {
        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            placeholderAPIEnabled = new MooTagsPlaceholderExpansion(this).register();
            StringManager.log(Level.INFO, "PlaceholderAPI expansion: %s", placeholderAPIEnabled);
        }

    }
}
