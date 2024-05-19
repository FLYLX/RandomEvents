package org.flylx.randevent;


import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.flylx.randevent.Events.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends JavaPlugin  {

    List<BukkitTask> taskList = new CopyOnWriteArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getServer().getScheduler().runTaskTimer(this , ()->{
            for(Player player : getServer().getOnlinePlayers()) {
                HandleEvent.displayCustomMessage(player);
            }
        },0,300);

    }

    @Override
    public void onDisable() {
        System.out.println("卸载");

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rd")) {
            if(sender instanceof Player){
                if(args.length == 1){
                    int choose = -10086;
                    try {
                        choose = Integer.parseInt(args[0]);

                    }catch (Throwable e){
                        if(args[0].equalsIgnoreCase("l")||args[0].equalsIgnoreCase("d")) {

                        }else{
                            sender.sendMessage("不是数字的话参数为l或d");
                        }
                    }
                    if(choose<RandEvents.values().length&&choose>=0) {

                        HandleEvent handleEvent = new HandleEvent(this , ((Player) sender).getWorld());
                        handleEvent.handleEvent(RandEvents.values()[choose]);

                    }else if(choose == -1){

                        taskList.add(getServer().getScheduler().runTaskTimer(this, () -> {
                            // 每个 tick 执行一次操作
                            // 这里可以写上你需要执行的逻辑判定
                            long currentTime = Bukkit.getWorld("world").getTime() % 24000;
                            if(currentTime == 0){
                                int rand = new Random().nextInt(RandEvents.values().length);
                                HandleEvent handleEvent = new HandleEvent(this,((Player) sender).getWorld());
                                handleEvent.handleEvent(RandEvents.values()[rand]);
                            }
                        }, 0, 1)); // 每 tick 执行一次

                    }else if(args[0].equalsIgnoreCase("l")){
                        for(BukkitTask bukkitTask : taskList){
                            sender.sendMessage("每日任务id："+String.valueOf(bukkitTask.getTaskId()));
                        }
                    }else if(args[0].equalsIgnoreCase("d")) {
                        for(BaseRandEvent baseRandEvent : HandleEvent.eventList){
                            sender.sendMessage("一次性任务："+baseRandEvent.getClass().getName());
                        }

                    }else{
                            sender.sendMessage("没有这个事件或参数");
                    }
                }else if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("l")||args[0].equalsIgnoreCase("d")) {
                        int number = 0;
                        try {
                            number = Integer.parseInt(args[1]);

                        }catch (Throwable e){
                            sender.sendMessage("第二个参数需要输入数字(起始为0)");
                        }
                        if(args[0].equalsIgnoreCase("l")){
                            if(number != -1) {
                                if(number<taskList.size()&&number>-1) {
                                    taskList.get(number).cancel();
                                    taskList.remove(number);
                                }else{
                                    sender.sendMessage("超过事件数量上/下限");
                                    return false;
                                }

                            }else {
                                for(BukkitTask bukkitTask:taskList){
                                    bukkitTask.cancel();
                                    taskList.remove(bukkitTask);
                                }
                            }

                        }else if(args[0].equalsIgnoreCase("d")){
                            if(number != -1) {
                                if(number<HandleEvent.eventList.size()&&number>-1) {
                                    HandleEvent.cancelDayEvent(HandleEvent.eventList.get(number));
                                    HandleEvent.eventList.remove(number);
                                }else{
                                    sender.sendMessage("超过事件数量上/下限");
                                    return false;
                                }
                            }else{
                                for(BaseRandEvent baseRandEvent:HandleEvent.eventList){
                                    HandleEvent.cancelDayEvent(baseRandEvent);
                                    HandleEvent.eventList.remove(baseRandEvent);
                                }
                            }
                        }
                    }else{
                        sender.sendMessage("需要输入参数l/d");
                    }

                    }else{
                    return false;
                }
            }
        }
        return false;
    }



}


