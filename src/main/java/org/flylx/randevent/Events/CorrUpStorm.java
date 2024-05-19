package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;

public class CorrUpStorm extends BaseRandEvent{
    Plugin plugin;

    public CorrUpStorm(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.CORRUPTSTORM);

    }

    public BukkitTask startCorrUpStorm(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(40);
            for(Player player:getWorld().getPlayers()){
                double health = player.getMaxHealth();
                player.setFoodLevel(player.getFoodLevel()-1);
                player.setHealth(player.getHealth()-health*0.05f);
            }
            if(timer.get()>=24000){
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                this.bukkitTask.cancel();
            }
        }, 0, 40)); // 每 tick 执行一次

        return getBukkitTask();
    }
}
