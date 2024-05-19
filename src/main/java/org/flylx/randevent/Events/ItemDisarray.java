package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemDisarray extends BaseRandEvent{
    Plugin plugin;

    public ItemDisarray(Plugin plugin , World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.ITEMDISARRAY);
    }

    public BukkitTask startItemDisarray(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(2400);
            for(Player player:getWorld().getPlayers()){
                ItemStack[] contents = player.getInventory().getContents();
                List<ItemStack> itemList = Arrays.asList(contents);
                Collections.shuffle(itemList, new Random());
                player.getInventory().setContents(itemList.toArray(new ItemStack[0]));
                player.updateInventory();
            }

            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 2400));
        return getBukkitTask();
    }
}
