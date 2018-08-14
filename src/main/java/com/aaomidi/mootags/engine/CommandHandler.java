package com.aaomidi.mootags.engine;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.commands.BaseCommand;
import com.aaomidi.mootags.commands.ReloadCommand;
import com.aaomidi.mootags.commands.SetCommand;
import com.aaomidi.mootags.model.MooTagsCommand;
import com.aaomidi.mootags.util.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by amir on 2016-01-08.
 */
public class CommandHandler implements CommandExecutor {
    private final MooTags instance;
    private final Map<String, MooTagsCommand> commands = new TreeMap<>();

    public CommandHandler(MooTags instance) {
        this.instance = instance;
        instance.getCommand("tags").setExecutor(this);
    }

    public void registerCommand(MooTagsCommand cmd) {
        //instance.getCommand(cmd.getName().toLowerCase()).setExecutor(this);
        commands.put(cmd.getName().toLowerCase(), cmd);
    }

    public void registerCommands() {
        new BaseCommand(instance, "base", "tags.use");
        new ReloadCommand(instance, "reload", "tags.reload");
        new SetCommand(instance, "set", "tags.set");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String commandLabel, String[] args) {
        if (!cmd.getName().equals("tags")) return false;

        if (args.length == 0) {
            commands.get("base").execute(cmd, commandSender, null);
            return true;
        }

        MooTagsCommand puffixCommand = commands.get(args[0].toLowerCase());
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        if (puffixCommand == null) {
            StringManager.sendMessage(commandSender, "Unrecognized command");
            return true;
        }

        if (!commandSender.hasPermission(puffixCommand.getPermission())) {
            StringManager.sendMessage(commandSender, "&cYou do not have permission to use that command.");
            return true;
        }

        puffixCommand.execute(cmd, commandSender, newArgs);
        return true;
    }
}
