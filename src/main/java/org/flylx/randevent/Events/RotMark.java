package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Random;

public class RotMark extends BaseRandEvent implements Listener {
    Plugin plugin;


    public RotMark(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.ROTMARK);
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
    public void RotMark(PlayerItemConsumeEvent event){
        if(getTaskFlag()){
            if (event.getItem() != null && event.getItem().getType().isEdible()) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10 * 20, 0));
            }
        }
    }
}
