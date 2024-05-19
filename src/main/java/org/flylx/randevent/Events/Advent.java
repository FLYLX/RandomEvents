package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;

public class Advent extends BaseRandEvent implements Listener {
    Plugin plugin;

    public Advent(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.ADVENT);
    }
    public BukkitTask startFlag(){
        setTaskFlag(true);
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
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
    public void Advent(EntityDamageByEntityEvent event){
        if(getTaskFlag()){
            if(event.getDamager() instanceof CraftPlayer && event.getDamager().getWorld().getName().equals(getWorld().getName())) {
                int randomNumber = new Random().nextInt(4);
                if (randomNumber == 2) {
                    event.setDamage(event.getDamage(EntityDamageEvent.DamageModifier.BASE)+10);
                }
            }
        }
    }
}
