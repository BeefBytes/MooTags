package com.aaomidi.mootags.commands;

import com.aaomidi.mootags.MooTags;
import com.aaomidi.mootags.model.MooTagsCommand;
import com.aaomidi.mootags.util.StringManager;
import io.mazenmc.menuapi.menu.ScrollingMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by amir on 2016-01-08.
 */
public class BaseCommand extends MooTagsCommand {
    public BaseCommand(MooTags instance, String name, String permission) {
        super(instance, name, permission);
    }

    @Override
    public boolean execute(Command command, CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            StringManager.sendMessage(commandSender, "This command is only executable via players.");
            return true;
        }
        Player player = (Player) commandSender;
        ScrollingMenu menu = getInstance().getGuiCreator().createInventoryForPlayer(player);

        menu.showTo(player);

        return true;
    }
}
