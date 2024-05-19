package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;

public class ThaumicKnowlege extends BaseRandEvent{
    Plugin plugin;

    public ThaumicKnowlege(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.THAUMICKNOWLEGE);
    }

    public BukkitTask startThaumicKnowlege(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(300);
            for(Player player:getWorld().getPlayers()){
                String[] args = {player.getName(),"Thaumcraft:ItemResource","1"};
                CoinGain.execute(player,"",args);
            }
            if(timer.get()>=24000){
                if(HandleEvent.eventList.contains(this)){
                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 300)); // 每 tick 执行一次

        return getBukkitTask();
    }
}
