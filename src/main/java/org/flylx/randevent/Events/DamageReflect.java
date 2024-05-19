package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
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

public class DamageReflect extends BaseRandEvent implements Listener {
    Plugin plugin;


    public DamageReflect(Plugin plugin , World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.DAMAGEREFLECT);
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
    public void DamageReflect(EntityDamageByEntityEvent event){
        if(getTaskFlag()){
            if(event.getEntity() instanceof CraftPlayer && event.getEntity().getWorld().getName().equals(getWorld().getName())) {
                int randomNumber = new Random().nextInt(4);
                if (randomNumber == 2) {
                    double damage = event.getDamage(EntityDamageEvent.DamageModifier.BASE);
                    event.setDamage(0.0d);
                    damage = damage*0.75;
                    if(event.getDamager() instanceof LivingEntity){
                        ((LivingEntity)event.getDamager()).damage(damage);
                    }
                }
            }
        }
    }
}
