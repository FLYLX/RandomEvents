package org.flylx.randevent.Events;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HolyLightDolores extends BaseRandEvent{

    Plugin plugin;

    public HolyLightDolores(Plugin plugin, World world) {
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.HOLYLIGHTDOLORES);
    }

    public BukkitTask startHolyLightDolores() {
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(1);
            for (Player player : getWorld().getPlayers()) {
                for(PotionEffect potionEffect : player.getActivePotionEffects()){

                    if(potionEffect.getType().equals(PotionEffectType.SLOW)||potionEffect.getType().equals(PotionEffectType.BLINDNESS)||potionEffect.getType().equals(PotionEffectType.HUNGER)
                    ||potionEffect.getType().equals(PotionEffectType.SLOW_DIGGING)||potionEffect.getType().equals(PotionEffectType.WEAKNESS)||potionEffect.getType().equals(PotionEffectType.WITHER)
                    ||potionEffect.getType().equals(PotionEffectType.POISON)||potionEffect.getType().equals(PotionEffectType.CONFUSION)){
                        player.removePotionEffect(potionEffect.getType());

                    }
                }
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 1)); // 每 tick 执行一次

        return getBukkitTask();
    }
}
