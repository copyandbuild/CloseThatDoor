package dev.larrox.closeThatDoor.cmd;

import dev.larrox.ChatUtil;
import dev.larrox.PlayerUtil;
import dev.larrox.closeThatDoor.CloseThatDoor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCloseThatDoor implements CommandExecutor {

    private final CloseThatDoor plugin;

    public CommandCloseThatDoor(CloseThatDoor plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CloseThatDoor.getInstance().reloadConfig();
        plugin.reloadConfig();
        ChatUtil.send(sender, "§8[§bCTD§8] §7Config Successfully reloaded!");

        return true;
    }
}
