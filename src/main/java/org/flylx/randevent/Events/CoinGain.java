package org.flylx.randevent.Events;

import com.google.common.base.Joiner;
import com.sun.javafx.geom.transform.Identity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.GiveCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.HandleEvent;
import org.flylx.randevent.RandEvents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CoinGain extends BaseRandEvent{
    Plugin plugin;

    public CoinGain(Plugin plugin, World world){
        this.plugin = plugin;
        setWorld(world);
        setEventType(RandEvents.COINGAIN);
    }

    public BukkitTask startCoinGain(){
        for(Player player :world.getPlayers()){
            player.sendMessage(getEventType().getDescriptionDisplay());
        }
        AtomicInteger timer = new AtomicInteger(0);
        setBukkitTask(Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            timer.addAndGet(1200);
            if(timer.get()>=18000) {
                for (Player player : getWorld().getPlayers()) {
                    String[] args = {player.getName(), "customnpcs:npcCoinStone", "1"};
                    execute(player, "", args);
                }
            }else{
                for (Player player : getWorld().getPlayers()) {
                    String[] args = {player.getName(), "customnpcs:npcCoinGold", "1"};
                    execute(player, "", args);
                }
            }
            if(timer.get()>=24000){
                if(HandleEvent.eventList.contains(this)){

                    HandleEvent.eventList.remove(this);
                }
                getBukkitTask().cancel();
            }
        }, 0, 1200)); // 每 tick 执行一次

        return getBukkitTask();
    }


    public static boolean execute(CommandSender sender, String currentAlias, String[] args) {
            Player player = Bukkit.getPlayerExact(args[0]);
            if (player != null) {
                Material material = Material.matchMaterial(args[1]);
                if (material == null) {
                    material = Bukkit.getUnsafe().getMaterialFromInternalName(args[1]);
                }

                if (material != null) {
                    int amount = 1;
                    short data = 0;
                    if (args.length >= 3) {
                        amount = getInteger(sender, args[2], 1, 64);
                        if (args.length >= 4) {
                            try {
                                data = Short.parseShort(args[3]);
                            } catch (NumberFormatException var11) {
                            }
                        }
                    }

                    ItemStack stack = new ItemStack(material, amount, data);
                    if (args.length >= 5) {
                        try {
                            stack = Bukkit.getUnsafe().modifyItemStack(stack, Joiner.on(' ').join(Arrays.asList(args).subList(4, args.length)));
                        } catch (Throwable var10) {
//                            player.sendMessage("Not a valid tag");
                            return true;
                        }
                    }

                    player.getInventory().addItem(new ItemStack[]{stack});
                } else {
//                    sender.sendMessage("There's no item called " + args[1]);
                }
            } else {
//                sender.sendMessage("Can't find player " + args[0]);
            }

            return true;
    }

    static int getInteger(CommandSender sender, String value, int min, int max) {
        return getInteger(sender, value, min, max, false);
    }

    static int getInteger(CommandSender sender, String value, int min, int max, boolean Throws) {
        int i = min;

        try {
            i = Integer.valueOf(value);
        } catch (NumberFormatException var8) {
            if (Throws) {
//                throw new NumberFormatException(String.format("%s is not a valid number", value));
            }
        }

        if (i < min) {
            i = min;
        } else if (i > max) {
            i = max;
        }

        return i;
    }
}
