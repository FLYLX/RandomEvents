package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.concurrent.atomic.AtomicInteger;

public class BrambleRing extends BaseRandEvent{
    Plugin plugin;
    public BrambleRing(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.BRAMBLERING);
    }

    public BukkitTask startBrambleRing(){

        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(20);
            for(Player player: getWorld().getPlayers()){
                if (timer.get() <= 6000) { // 小于等于5分钟，1点伤害，9格范围
                    damagePlayersAround(player, 2, 1);
                } else if (timer.get() <= 18000) { // 大于5分钟小于等于15分钟，3点伤害，9格范围
                    damagePlayersAround(player, 3, 3);
                } else { // 大于15分钟，3点伤害，49格范围
                    damagePlayersAround(player, 4, 3);
                }
            }
            if (timer.get() >= 24000) {
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 20));
        return getBukkitTask();
    }

    public void damagePlayersAround(Player player, int range, int damage) {
        for (LivingEntity target : player.getWorld().getLivingEntities()) {
            if (target.getLocation().distanceSquared(player.getLocation()) <= range) {
                if(target instanceof Player){
                    if(((Player) target).getName().equals(player.getName())){

                    }else{
                        target.damage(damage);
                    }
                }else{
                    target.damage(damage);
                }
            }
        }
    }
}
