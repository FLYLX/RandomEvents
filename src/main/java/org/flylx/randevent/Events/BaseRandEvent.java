package org.flylx.randevent.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.flylx.randevent.RandEvents;

public class BaseRandEvent implements Listener {

    BukkitTask bukkitTask;
    boolean taskFlag = false;
    World world;
    RandEvents eventType;

    public void setBukkitTask(BukkitTask bukkitTask){
        this.bukkitTask = bukkitTask;
    }

    public BukkitTask getBukkitTask(){
        return this.bukkitTask;
    }

    public void setTaskFlag(boolean taskFlag){
        this.taskFlag = taskFlag;
    }

    public boolean getTaskFlag(){
        return this.taskFlag;
    }

    public void setWorld(World world){
        this.world = world;
    }

    public World getWorld(){
        return this.world;
    }

    public void setEventType(RandEvents eventType){
        this.eventType = eventType;
    }

    public RandEvents getEventType(){
        return this.eventType;
    }


}
