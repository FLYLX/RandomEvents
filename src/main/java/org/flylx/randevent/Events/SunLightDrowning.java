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

public class SunLightDrowning extends BaseRandEvent{
    Plugin plugin;
    public SunLightDrowning(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.SUNLIGHTDROWNING);
    }

    public BukkitTask startSunLightDrowning(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(20);
            for(Player player:getWorld().getPlayers()){
                if (player.getLocation().getBlock().getLightFromSky() == 15) {
                        player.setRemainingAir(1); // 让玩家开始溺水
                        player.damage(1); // 造成 1 点伤害

                }
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){
                    HandleEvent.eventList.remove(this);
                }
                this.bukkitTask.cancel();
            }
        }, 0, 20));
        return getBukkitTask();
    }
}
