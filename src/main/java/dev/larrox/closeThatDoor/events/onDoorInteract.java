package dev.larrox.closeThatDoor.events;

import dev.larrox.TaskUtil;
import dev.larrox.closeThatDoor.CloseThatDoor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onDoorInteract implements Listener {

    private final CloseThatDoor plugin;
    private final TaskUtil taskUtil;

    public onDoorInteract(CloseThatDoor plugin) {
        this.plugin = plugin;
        this.taskUtil = new TaskUtil(plugin);
    }

    @EventHandler
    public void onDoorInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        Material type = block.getType();
        Player player = event.getPlayer();

        if (!type.name().endsWith("_DOOR")) return;

        taskUtil.runLater(() -> {
            Openable door = (Openable) block.getBlockData();
            if (door.isOpen()) {
                int delaySeconds = plugin.getConfig().getInt("door-close-delay");

                taskUtil.runLater(() -> {
                    Openable check = (Openable) block.getBlockData();
                    if (check.isOpen()) {
                        check.setOpen(false);
                        block.setBlockData(check);
                        player.playSound(block.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 0.5f, 0.5f);
                    }
                }, 0, 0, 0, delaySeconds, 0);
            }
        }, 0, 0, 0, 0, 1);
    }
}
