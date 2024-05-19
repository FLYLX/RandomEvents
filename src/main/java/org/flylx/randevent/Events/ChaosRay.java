package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ChaosRay extends BaseRandEvent{
    Plugin plugin;

    PotionEffectType[] potionEffectTypes = {
            PotionEffectType.WITHER,
            PotionEffectType.POISON,
            PotionEffectType.SLOW
    };

    public ChaosRay(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.CHAOSRAY);
    }

    public BukkitTask startChaosRay(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }

        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(400);
            for(Player player:getWorld().getPlayers()){
                int rand = new Random().nextInt(potionEffectTypes.length);
                player.addPotionEffect(potionEffectTypes[rand].createEffect(160,1));
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 400));
        return getBukkitTask();
    }
}
