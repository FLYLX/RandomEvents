package org.flylx.randevent.Events;

import net.minecraft.server.v1_7_R4.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.LogManager;

public class DurabilityLoss extends BaseRandEvent{
    Plugin plugin;
    public DurabilityLoss(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.DURABILITYLOSS);
    }

    public BukkitTask startDurabilityLoss(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(300);
            for(Player player:getWorld().getPlayers()){
                for(ItemStack itemStack : player.getInventory().getContents()){
                    if (itemStack != null && itemStack.getType() != Material.AIR && itemStack.getType().getMaxDurability() > 0 && itemStack.getDurability() < itemStack.getType().getMaxDurability()) {
                        itemStack.setDurability((short) (itemStack.getDurability() + 1)); // 减少物品耐久度
                    }
                }
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 300));
        return getBukkitTask();
    }
}
