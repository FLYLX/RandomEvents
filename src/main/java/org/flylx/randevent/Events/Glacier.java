package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Glacier extends BaseRandEvent implements Listener {
    Plugin plugin;

    static Map<String, Long> frozeFlag = new HashMap<>();
    static Map<String, Boolean> frozeChooseFlag = new HashMap<>();

    public Glacier(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.GLACIER);
    }
    public BukkitTask startFlag(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setTaskFlag(true);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin,()->{
            timer.addAndGet(1);
            Set<Map.Entry<String, Long>> set = frozeFlag.entrySet();
            set.stream().forEach(stringIntegerEntry -> {
                stringIntegerEntry.setValue(stringIntegerEntry.getValue()+1);
            });
            if(timer.get()%600 == 0) {
                for (Player player : getWorld().getPlayers()) {
                    int rand = new Random().nextInt(2);
                    if (rand == 1) {
                        this.frozeChooseFlag.put(player.getName(), true);
                        this.frozeFlag.put(player.getName(),0l);
                    }
                }
            }
            for (Player player : getWorld().getPlayers()) {
                if(this.frozeChooseFlag.containsKey(player.getName())) {
                    if (this.frozeChooseFlag.get(player.getName())) {
                        if (this.frozeFlag.get(player.getName()) > 60) {
                            this.frozeChooseFlag.put(player.getName(), false);
                            this.frozeFlag.put(player.getName(), 0l);
                        }
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
    public void Glacier(PlayerMoveEvent event){
        if(getTaskFlag()){
            if(this.frozeChooseFlag.containsKey(event.getPlayer().getName())) {
                if(this.frozeChooseFlag.get(event.getPlayer().getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
