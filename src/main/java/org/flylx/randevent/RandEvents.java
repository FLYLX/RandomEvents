package org.flylx.randevent;

public enum RandEvents {
    //增益BUFF：
    //玩家每三秒回复5%生命值
    LIFERECOVERY("生命恢复","玩家每三秒回复5%生命值"),
    //每分钟获得1石币
    COINGAIN("贪婪之雨","每分钟获得1石币"),
    //获得力量3，速度3，跳跃提升3
    EFFECTIMPROVE("秩序之躯","力量3，速度3,跳跃提升3"),
    //每分钟随机给予玩家煤块，金块，铁块，钻石块，红石块，青金石块的其中一种
    BLOCKGAIN("丰饶","每30秒随机给予玩家煤块，金块，铁块，钻石块，红石块，青金石，原始珍珠（单独概率减半）块的其中一种"),
    //每次受到伤害有25%的概率无视此次伤害
    SHIELDSWIPE("强效护盾","每次受到伤害有25%的概率无视此次伤害"),
    //每分钟有0.2%的概率获得一个钻石币
    DIAMONDGAIN("万里挑一的幸运","每分钟有25%的概率获得一个钻石币"),
    //[多洛莉丝之光]无视所有负面BUFF
    HOLYLIGHTDOLORES("多洛莉丝之光","无视所有负面BUFF"),
    //【奥法学识】每过15秒获得一个知识碎片
    THAUMICKNOWLEGE("奥法学识","每过15秒获得一个知识碎片"),
//    //攻击距离+1格
//    ATTACKRANGEEXTEND("攻击延伸(未完成)"),
//    [莉莉丝之触]玩家所有攻击都能恢复自己生命值，回复量固定为2点，CD为3秒
    LILYTOUCHLILITH("莉莉丝之触","玩家所有攻击都能恢复自己生命值，回复量固定为2点，CD为3秒"),
    //每次受到伤害有25%的概率返还目标，返还的伤害为造成的75%
    DAMAGEREFLECT("咒法巫毒","每次受到伤害有25%的概率返还目标，返还的伤害为造成的75%"),
    //[降临]每次攻击有25%概率使得此次伤害+10
    ADVENT("降临","每次攻击有25%概率使得此次伤害+10"),
    //每分钟都有50%的概率获得抗性5持续五秒
    RESISTANCEBOOST("傲慢","每30秒都有50%的概率获得抗性5持续五秒"),
    //每过5分钟，你的下一次攻击会对目标造成最大生命值50%的伤害
    LEGALDEATH("律令死亡","每过2分钟，你的下一次攻击会对目标造成当前生命值50%的伤害"),
    BRAMBLERING("荆棘环刃","玩家周围1格的范围每秒造成1点伤害，5分钟时候伤害变成每秒3点，15分钟的时候范围变成玩家周围的3格"),
    //负面BUFF
    //[腐化风暴]玩家每秒失去5%的生命值
    CORRUPTSTORM("腐化风暴","玩家每秒失去5%的生命值"),
    //生命上限减少25%
    HEALTHREDUCTION("生命衰弱","生命上限减少25%"),
    //全身物品每分钟失去1%的耐久
    DURABILITYLOSS("耐久风暴","全身物品每15秒失去1的耐久"),
    //失明1
    BLINDNESS("失明","失明1，间歇性"),
    //[肌无力]虚弱5
    MUSCLEWEAKNESS("肌肉衰竭","虚弱5，间歇性"),
    //背包物品会被打乱，2分钟打乱一次
    ITEMDISARRAY("幽灵之手","背包物品会被打乱，2分钟打乱一次"),
    //无法疾跑
    SPRINTDISABLE("腿麻了","无法疾跑"),
    //无法获得任何BUFF的效果
    BUFFIMMUNITY("混沌魔女的诅咒","无法获得任何buff的效果"),
    //被阳光直射会获得溺水BUFF
    SUNLIGHTDROWNING("溺亡的烈阳","被阳光直射会获得溺水buff"),
    //[重力爆破]从3格高度跳下会掉血例如3格-1，4格-2以此类推，且此伤害是真伤
    GRAVITYEXPLOSION("重力爆破","从3格高度跳下会掉血例如3格-10,4格-11以此类推，且此伤害是真伤。"),
    //玩家每移动10格都有3%的概率失去25%的生命值
    MOVEHAZARD("灵魂偏移","玩家每移动10格都有30%的概率失去25%的生命值"),
    //每次攻击生物都有5%的概率使自己获得凋零1持续5秒
    WITHERING("恶咒","每次攻击生物都有30%的概率使自己获得凋零1持续5秒"),
    VOICELOSS("失声瘟疫","在这种天气下，主世界的玩家发言具有传染性，这时玩家的字会显示为绿色。其他世界玩家要是在这名玩家后发言将会被传染。感染的玩家5分钟内不喝牛奶将会死亡。"),
    THALASSOPHOBIA("海渊恐惧症","玩家进入水中会每秒减少5%的生命值"),
    GLACIER("冰河","每过30秒玩家都有50%的概率被冻住3秒"),
    SHADOWSRAIN("阴影之雨","在这种事件下，天气将会被切换成雨天，玩家被雨淋中每秒会减少1的生命值和饱食度"),
    FALLINGSUN("坠日","每过15秒，都有25%的概率在玩家脚底下生成火焰，火焰5秒后熄灭"),
    CHAOSRAY("混沌之地的辐射","每过20秒，都有30%的概率获得凋零2，中毒2，缓慢2其中的一种持续8秒。"),
    ROTMARK("腐烂印记","玩家吃食物将会获得中毒1的效果持续10秒");

//    //无法通过任何方式恢复血量
//    NOREGENERATION("锁血");

    private final String description;
    private final String descriptionDisplay;


    RandEvents(String description,String descriptionDisplay) {
        this.description = description;
        this.descriptionDisplay = descriptionDisplay;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionDisplay(){
        return descriptionDisplay;
    }

}
