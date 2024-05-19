package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;

public class HealthReduction extends BaseRandEvent{
    Plugin plugin;

    public HealthReduction(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.HEALTHREDUCTION);
    }

    public BukkitTask startHealthReduction(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        for(Player player : getWorld().getPlayers()){
            player.setMaxHealth((double) player.getMaxHealth()*0.75d);
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(1);

            if (timer.get() >= 23998||!getTaskFlag()) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                for(Player player:Bukkit.getServer().getOnlinePlayers()){
                    player.setMaxHealth(20.0d);
                }
                setTaskFlag(true);
                getBukkitTask().cancel();
            }
        }, 0, 1));
        return getBukkitTask();
    }
}
