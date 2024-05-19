package org.flylx.randevent.Events;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class VoiceLoss extends BaseRandEvent implements Listener {
    Plugin plugin;

    static String lastPlayer = null;
    static Map<String, Long> voiceFlag = new HashMap<>();

    public VoiceLoss(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.VOICELOSS);
    }
    public BukkitTask startFlag(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        setTaskFlag(true);
        voiceFlag.clear();
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin,()->{
            timer.addAndGet(1);
            Set<Map.Entry<String, Long>> set = voiceFlag.entrySet();
            set.stream().forEach(stringIntegerEntry -> {
                stringIntegerEntry.setValue(stringIntegerEntry.getValue()+1);
            });
            for(Player player:world.getPlayers()){
                String name = player.getName();
                if(!this.voiceFlag.containsKey(name)){
                    this.voiceFlag.put(name,0l);
                }else{
                    if(this.voiceFlag.get(name)>=6000){
                        player.setHealth(0);
                        this.voiceFlag.remove(name);
                    }
                }
            }
            if(timer.get()>=24000) {
                if (HandleEvent.eventList.contains(this)) {
                    HandleEvent.eventList.remove(this);
                }
                setTaskFlag(false);
                getBukkitTask().cancel();
            }
        },0,1));
        return getBukkitTask();
    }

    @EventHandler
    public void VoiceLoss(AsyncPlayerChatEvent event){
        if(getTaskFlag()){
            if(voiceFlag.containsKey(event.getPlayer().getName())) {
                event.setFormat(ChatColor.RED + event.getFormat());
                lastPlayer = event.getPlayer().getName();
            }else{
                if(lastPlayer != null){
                    this.voiceFlag.put(lastPlayer,0l);
                }
                lastPlayer = null;
            }
        }
    }


    @EventHandler
    public void drinkMilk(PlayerInteractEvent event){
        if(getTaskFlag()){
            if (event.getItem() != null && event.getItem().getType() == Material.MILK_BUCKET) {
                this.voiceFlag.put(event.getPlayer().getName(),0l);
            }
        }
    }
}
