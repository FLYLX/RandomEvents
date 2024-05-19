package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FallingSun extends BaseRandEvent{
    Plugin plugin;

    public FallingSun(Plugin plugin, World world) {
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.FALLINGSUN);

    }

    public BukkitTask startFallingSun() {
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(300);

            for (Player player : getWorld().getPlayers()) {
                int rand = new Random().nextInt(4);
                if(rand == 2) {
                    Location playerLoc = player.getLocation();
                    playerLoc.getWorld().getBlockAt(playerLoc.subtract(0, 0, 0)).setType(Material.FIRE);
                }
                if(player.getLocation().getBlock().getType()  == Material.FIRE){
                    player.damage(3);
                }

            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 300)); // 每 tick 执行一次



        return getBukkitTask();
    }
}
