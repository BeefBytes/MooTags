package com.aaomidi.mootags.model;

import com.aaomidi.mootags.MooTags;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by amir on 2015-11-30.
 */
public abstract class MooTagsCommand {
    @Getter
    private final MooTags instance;
    @Getter
    private final String name;
    @Getter
    private final String permission;

    public MooTagsCommand(MooTags instance, String name, String permission) {
        this.instance = instance;
        this.name = name;
        this.permission = permission;

        instance.getCommandHandler().registerCommand(this);
    }

    public abstract boolean execute(Command command, CommandSender commandSender, String[] args);

}
