package org.flylx.randevent.Events;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockGain extends BaseRandEvent{
    String[] randBlocks = {
            "minecraft:redstone_block",
            "minecraft:coal_block",
            "minecraft:gold_block",
            "minecraft:diamond_block",
            "minecraft:lapis_block",
            "minecraft:emerald_block",
            "Thaumcraft:ItemEldritchObject"

    };
        Plugin plugin;

        public BlockGain(Plugin plugin, World world) {
            this.plugin = plugin;
            setWorld(world);
            setEventType(RandEvents.BLOCKGAIN);
        }

        public BukkitTask startBlockGain() {
            for(Player player :world.getPlayers()){
                player.sendMessage(getEventType().getDescriptionDisplay());
            }
            AtomicInteger timer = new AtomicInteger(0);
            setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                timer.addAndGet(600);
                for (Player player : getWorld().getPlayers()) {
                    //最后一个减少双倍几率
                    int rand = new Random().nextInt(randBlocks.length*2 - 1);
                    String[] args = {player.getName(), randBlocks[rand/2], "1"};
                    CoinGain.execute(player, "", args);
                }
                if (timer.get() >= 24000) {
                    if(HandleEvent.eventList.contains(this)){

                        HandleEvent.eventList.remove(this);
                    }
                    getBukkitTask().cancel();
                }
            }, 0, 600)); // 每 tick 执行一次



            return getBukkitTask();
    }
}
