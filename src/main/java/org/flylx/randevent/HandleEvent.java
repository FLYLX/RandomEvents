package org.flylx.randevent;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.flylx.randevent.Events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HandleEvent {

    World world;
    Plugin plugin;
    BukkitTask bukkitTask;

    public static List<BaseRandEvent> eventList = new CopyOnWriteArrayList<>();


    public HandleEvent(Plugin plugin , World world){
        this.plugin = plugin;
        this.world = world;
    }

    public void handleEvent(RandEvents randEvents) {

//        if(bukkitTask != null) {
//            //有flag的记得弄成false
//            for(BaseRandEvent baseRandEvent : eventList){
//                cancelDayEvent(baseRandEvent);
//            }
//        }


        switch (randEvents) {
            // 增益Buff
            case LIFERECOVERY:
                LifeRecovery lifeRecovery = new LifeRecovery(plugin , world);
                bukkitTask = lifeRecovery.startLifeRecovery();
                eventList.add(lifeRecovery);
                break;
            case COINGAIN:
                CoinGain coinGain = new CoinGain(plugin , world);
                bukkitTask = coinGain.startCoinGain();
                eventList.add(coinGain);
                break;
            case EFFECTIMPROVE:
                EffectImprove effectImprove = new EffectImprove(plugin , world);
                bukkitTask = effectImprove.startEffectImprove();
                eventList.add(effectImprove);
                break;
            case BLOCKGAIN:
                BlockGain blockGain = new BlockGain(plugin , world);
                bukkitTask = blockGain.startBlockGain();
                eventList.add(blockGain);
                break;
            case SHIELDSWIPE:
                ShieldSwipe shieldSwipe = new ShieldSwipe(plugin , world);
                bukkitTask = shieldSwipe.startFlag();
                Bukkit.getPluginManager().registerEvents(shieldSwipe,plugin);
                eventList.add(shieldSwipe);
                break;
            case DIAMONDGAIN:
                DiamondGain diamondgain = new DiamondGain(plugin , world);
                bukkitTask = diamondgain.startDiamondGain();
                eventList.add(diamondgain);
                break;
            case HOLYLIGHTDOLORES:
                HolyLightDolores holyLightDolores = new HolyLightDolores(plugin , world);
                bukkitTask = holyLightDolores.startHolyLightDolores();
                eventList.add(holyLightDolores);
                break;
            case LILYTOUCHLILITH:
                LilyTouchLilith lilyTouchLilith = new LilyTouchLilith(plugin , world);
                bukkitTask = lilyTouchLilith.startFlag();
                Bukkit.getPluginManager().registerEvents(lilyTouchLilith,plugin);
                eventList.add(lilyTouchLilith);
                break;
            case DAMAGEREFLECT:
                DamageReflect damageReflect = new DamageReflect(plugin , world);
                bukkitTask = damageReflect.startFlag();
                Bukkit.getPluginManager().registerEvents(damageReflect,plugin);
                eventList.add(damageReflect);
                break;
            case ADVENT:
                Advent advent = new Advent(plugin , world);
                bukkitTask = advent.startFlag();
                Bukkit.getPluginManager().registerEvents(advent,plugin);
                eventList.add(advent);
                break;
            case RESISTANCEBOOST:
                ResistanceBoost resistanceBoost = new ResistanceBoost(plugin , world);
                bukkitTask = resistanceBoost.startResistanceBoost();
                eventList.add(resistanceBoost);
                break;
            case LEGALDEATH:
                LegalDeath legalDeath = new LegalDeath(plugin,world);
                bukkitTask = legalDeath.startFlag();
                Bukkit.getPluginManager().registerEvents(legalDeath,plugin);
                eventList.add(legalDeath);
                break;
            case BRAMBLERING:
                BrambleRing brambleRing = new BrambleRing(plugin,world);
                bukkitTask = brambleRing.startBrambleRing();
                eventList.add(brambleRing);
                break;
            case CORRUPTSTORM:
                CorrUpStorm corrUpStorm = new CorrUpStorm(plugin , world);
                bukkitTask = corrUpStorm.startCorrUpStorm();
                eventList.add(corrUpStorm);
                break;
            case HEALTHREDUCTION:
                HealthReduction healthReduction = new HealthReduction(plugin , world);
                bukkitTask = healthReduction.startHealthReduction();
                eventList.add(healthReduction);
                break;
            case DURABILITYLOSS:
                DurabilityLoss durabilityLoss = new DurabilityLoss(plugin , world);
                bukkitTask = durabilityLoss.startDurabilityLoss();
                eventList.add(durabilityLoss);
                break;
            case BLINDNESS:
                BlindNess blindNess = new BlindNess(plugin , world);
                bukkitTask = blindNess.startBlindNess();
                eventList.add(blindNess);
                break;
            case MUSCLEWEAKNESS:
                MuscleWeakness muscleWeakness = new MuscleWeakness(plugin , world);
                bukkitTask = muscleWeakness.startMuscleWeakness();
                eventList.add(muscleWeakness);
                break;
            case ITEMDISARRAY:
                ItemDisarray itemDisarray = new ItemDisarray(plugin , world);
                bukkitTask = itemDisarray.startItemDisarray();
                eventList.add(itemDisarray);
                break;
            case SPRINTDISABLE:
                SprintDisable sprintDisable = new SprintDisable(plugin , world);
                bukkitTask = sprintDisable.startFlag();
                Bukkit.getPluginManager().registerEvents(sprintDisable,plugin);
                eventList.add(sprintDisable);
                break;
            case BUFFIMMUNITY:
                BuffImmunity buffImmunity = new BuffImmunity(plugin , world);
                bukkitTask = buffImmunity.startBuffImmunity();
                eventList.add(buffImmunity);
                break;
            case SUNLIGHTDROWNING:
                SunLightDrowning sunLightDrowning = new SunLightDrowning(plugin , world);
                bukkitTask = sunLightDrowning.startSunLightDrowning();
                eventList.add(sunLightDrowning);
                break;
            case GRAVITYEXPLOSION:
                GravityExplosion gravityExplosion = new GravityExplosion(plugin , world);
                bukkitTask = gravityExplosion.startGravityExplosion();
                eventList.add(gravityExplosion);
                break;
            case MOVEHAZARD:
                MoveHazard moveHazard = new MoveHazard(plugin , world);
                bukkitTask = moveHazard.startFlag();
                Bukkit.getPluginManager().registerEvents(moveHazard,plugin);
                eventList.add(moveHazard);
                break;
            case WITHERING:
                WitherRing witherRing = new WitherRing(plugin , world);
                bukkitTask = witherRing.startFlag();
                Bukkit.getPluginManager().registerEvents(witherRing,plugin);
                eventList.add(witherRing);
                break;

            case THAUMICKNOWLEGE:
                ThaumicKnowlege thaumicKnowlege = new ThaumicKnowlege(plugin,world);
                bukkitTask = thaumicKnowlege.startThaumicKnowlege();
                eventList.add(thaumicKnowlege);
                break;

            case VOICELOSS:
                VoiceLoss voiceLoss = new VoiceLoss(plugin,world);
                bukkitTask = voiceLoss.startFlag();
                Bukkit.getPluginManager().registerEvents(voiceLoss,plugin);
                eventList.add(voiceLoss);
                break;

            case THALASSOPHOBIA:
                Thalassophobia thalassophobia = new Thalassophobia(plugin,world);
                bukkitTask = thalassophobia.startThalassophobia();
                eventList.add(thalassophobia);
                break;
            case GLACIER:
                Glacier glacier = new Glacier(plugin,world);
                bukkitTask = glacier.startFlag();
                Bukkit.getPluginManager().registerEvents(glacier,plugin);
                eventList.add(glacier);
                break;
            case SHADOWSRAIN:
                ShadowsRain shadowsRain = new ShadowsRain(plugin,world);
                bukkitTask = shadowsRain.startShadowsRain();
                eventList.add(shadowsRain);
                break;
            case FALLINGSUN:
                FallingSun fallingSun = new FallingSun(plugin,world);
                bukkitTask = fallingSun.startFallingSun();
                eventList.add(fallingSun);
                break;
            case CHAOSRAY:
                ChaosRay chaosRay = new ChaosRay(plugin,world);
                bukkitTask = chaosRay.startChaosRay();
                eventList.add(chaosRay);
                break;
            case ROTMARK:
                RotMark rotMark = new RotMark(plugin,world);
                bukkitTask = rotMark.startFlag();
                Bukkit.getPluginManager().registerEvents(rotMark,plugin);
                eventList.add(rotMark);
                break;
        }
        for (Player player:Bukkit.getServer().getOnlinePlayers()){
            displayCustomMessage(player);
        }
    }

    public static void displayCustomMessage(Player player) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(scoreboard);
        Objective objective1 = scoreboard.registerNewObjective(player.getWorld().getName(), "dummy");
        objective1.setDisplaySlot(DisplaySlot.SIDEBAR);
        for(BaseRandEvent baseRandEvent : eventList){
                if(player.getWorld().getUID().toString().equals(baseRandEvent.getWorld().getUID().toString())) {
                    objective1.getScore(baseRandEvent.getEventType().getDescription()).setScore(1);
            }

        }

    }

    public static void cancelDayEvent(BaseRandEvent baseRandEvent){
            baseRandEvent.setTaskFlag(false);
            baseRandEvent.getBukkitTask().cancel();
            HandlerList.unregisterAll(baseRandEvent);
            eventList.remove(baseRandEvent);
    }

}
