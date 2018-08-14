package com.aaomidi.mootags.commands;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.MooTagsCommand;
import com.aaomidi.mootags.model.Tag;
import com.aaomidi.mootags.model.TagPlayer;
import com.aaomidi.mootags.registry.PlayerRegistry;
import com.aaomidi.mootags.registry.TagRegistry;
import com.aaomidi.mootags.util.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by amir on 2016-01-08.
 */
public class SetCommand extends MooTagsCommand {
    public SetCommand(MooTags instance, String name, String permission) {
        super(instance, name, permission);
    }

    @Override
    public boolean execute(Command command, CommandSender commandSender, String[] args) {
        if (args.length < 2) {
            StringManager.sendMessage(commandSender, "&cPlease enter the player name/uuid!");
            return true;
        }

        String input = args[0];
        String tagID = args[1];

        Tag tag = TagRegistry.getTag(tagID);

        if (tag == null) {
            StringManager.sendMessage(commandSender, "&cThat tag was not recongized");
            return true;
        }

        TagPlayer tagPlayer = null;
        if (input.length() > 16) {
            try {
                UUID uuid = UUID.fromString(input);
                tagPlayer = PlayerRegistry.getPlayer(uuid);
            } catch (Exception ex) {
                tagPlayer = null;
            }
        } else {
            Player player = Bukkit.getPlayer(input);
            if (player != null) {
                tagPlayer = PlayerRegistry.getPlayer(player.getUniqueId());
            }
        }

        if (tagPlayer == null) {
            StringManager.sendMessage(commandSender, "&cThat player was not recognized.");
            return true;
        }

        tagPlayer.setTagID(tagID);
        return true;
    }
}
