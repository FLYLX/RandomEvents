package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class LegalDeath extends BaseRandEvent implements Listener {
    Plugin plugin;

    static Map<String, Long> attackFlag = new HashMap<>();

    public LegalDeath(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.LEGALDEATH);
    }
    public BukkitTask startFlag(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setTaskFlag(true);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin,()->{
            timer.addAndGet(1);
            Set<Map.Entry<String, Long>> set = attackFlag.entrySet();
            set.stream().forEach(stringIntegerEntry -> {
                stringIntegerEntry.setValue(stringIntegerEntry.getValue()+1);
            });
            for(Player player:world.getPlayers()){
                if(attackFlag.containsKey(player.getName())){
                    if(attackFlag.get(player.getName())==2400l){
                        player.sendMessage("律令死亡已就绪");
                    }
                }
            }
            if(timer.get()>=24000){
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
                setTaskFlag(false);
            }
        },0,1));

        return getBukkitTask();
    }

    @EventHandler
    public void LegalDeath(EntityDamageByEntityEvent event){
        if(getTaskFlag()){
            if(event.getDamager() instanceof CraftPlayer && event.getDamager().getWorld().getName().equals(getWorld().getName())) {
                String name = ((CraftPlayer) event.getDamager()).getName();
                if(!this.attackFlag.containsKey(name)){
                    this.attackFlag.put(name,0l);
                    event.setDamage(((CraftLivingEntity)event.getEntity()).getMaxHealth()*0.5d);

                }else{
                    if(this.attackFlag.get(name)>=2400l){
                        event.setDamage(((CraftLivingEntity)event.getEntity()).getMaxHealth()*0.5d);
                        this.attackFlag.put(name,0l);
                    }
                }
            }
        }
    }


}
