package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;

public class EffectImprove extends BaseRandEvent{
    Plugin plugin;

    public EffectImprove(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.EFFECTIMPROVE);
    }

    public BukkitTask startEffectImprove(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(300);
            for(Player player:getWorld().getPlayers()){
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(300,2));
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(300,2));
                player.addPotionEffect(PotionEffectType.JUMP.createEffect(300,2));
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 300));
        return getBukkitTask();
    }
}
