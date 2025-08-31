package dev.larrox.closeThatDoor;

import dev.larrox.closeThatDoor.cmd.CommandCloseThatDoor;
import dev.larrox.closeThatDoor.events.onDoorInteract;
import org.bukkit.plugin.java.JavaPlugin;

public final class CloseThatDoor extends JavaPlugin {

    private static CloseThatDoor instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new onDoorInteract(this), this);

        getCommand("closethatdoor").setExecutor(new CommandCloseThatDoor(this));
    }

    public static CloseThatDoor getInstance() {
        return instance;
    }
}
