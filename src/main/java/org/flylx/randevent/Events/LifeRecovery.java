package org.flylx.randevent.Events;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;


public class LifeRecovery extends BaseRandEvent{
    Plugin plugin;
    public LifeRecovery(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.LIFERECOVERY);
    }

    public BukkitTask startLifeRecovery(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(60);
            for(Player player:getWorld().getPlayers()){
                double health = player.getMaxHealth();
                health = health+health*0.05f;
                if(health>=player.getMaxHealth()){
                    player.setHealth(player.getMaxHealth());
                }else{
                    player.setHealth(health);
                }
            }
            if(timer.get()>=24000){
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 60)); // 每 tick 执行一次

        return getBukkitTask();
    }

}
