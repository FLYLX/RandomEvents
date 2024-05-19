package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;

public class WitherRing extends BaseRandEvent implements Listener {
    Plugin plugin;
    public WitherRing(Plugin plugin , World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.WITHERING);
    }
    public BukkitTask startFlag(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        setTaskFlag(true);
        setBukkitTask(Bukkit.getScheduler().runTaskLater(plugin,()->{
            if(HandleEvent.eventList.contains(this)){
                HandleEvent.eventList.remove(this);
            }
            setTaskFlag(false);
            getBukkitTask().cancel();
        },24000));
        return getBukkitTask();
    }

    @EventHandler
    public void WitherRing(EntityDamageByEntityEvent event){
        if(getTaskFlag()){
            if(event.getEntity() instanceof CraftPlayer&&event.getEntity().getWorld().getName().equals(getWorld().getName())) {
                int randomNumber = new Random().nextInt(10);
                if (randomNumber < 3) {
                    ((CraftPlayer)event.getEntity()).addPotionEffect(PotionEffectType.WITHER.createEffect(1000,0));
                }
            }
        }
    }
}
