package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class DiamondGain extends BaseRandEvent {
    Plugin plugin;

    public DiamondGain(Plugin plugin, World world) {
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.DIAMONDGAIN);

    }

    public BukkitTask startDiamondGain() {
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(1200);

            for (Player player : getWorld().getPlayers()) {
                int rand = new Random().nextInt(20);
                if(rand == 10) {
                    String[] args = {player.getName(), "minecraft:redstone_block", "1"};
                    CoinGain.execute(player, "", args);
                }

            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 1200)); // 每 tick 执行一次



        return getBukkitTask();
    }

}
