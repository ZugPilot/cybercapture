package de.zugpilot.cybercapture.model.game.features.process.types.reward;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public abstract class AbstractReward implements Reward {

    private final String rewardName;
    private final double chance;

    /*
    True -> Reward will be executed
    False -> Punishment will be executed
     */

    public RewardResult handleChance(){
        Random random = new Random();
        if(chance > random.nextDouble() * 100){
            return RewardResult.REWARDED;
        }
        return RewardResult.PUNISHED;
    }

}
