package com.aaomidi.mootags.commands;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.MooTagsCommand;
import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.registry.TagRegistry;
import com.aaomidi.mootags.util.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by amir on 2016-01-08.
 */
public class ReloadCommand extends MooTagsCommand {
    public ReloadCommand(MooTags instance, String name, String permission) {
        super(instance, name, permission);
    }

    @Override
    public boolean execute(Command command, CommandSender commandSender, String[] args) {
        TagRegistry.reset();
        PlayerRegistry.reset();

        getInstance().getDataManager().readDataFiles();
        StringManager.sendMessage(commandSender, "&dReloaded files.");
        return true;
    }
}
