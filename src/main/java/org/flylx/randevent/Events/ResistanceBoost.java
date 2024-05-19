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

public class ResistanceBoost extends BaseRandEvent{

    Plugin plugin;

    public ResistanceBoost(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.RESISTANCEBOOST);
    }

    public BukkitTask startResistanceBoost(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(600);
            for(Player player:getWorld().getPlayers()){
                int rand = new Random().nextInt(2);
                if(rand == 1){
                    player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(100,4));

                }
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 600));
        return getBukkitTask();
    }
}
