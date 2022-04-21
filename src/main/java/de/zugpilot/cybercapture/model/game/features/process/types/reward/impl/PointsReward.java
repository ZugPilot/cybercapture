package de.zugpilot.cybercapture.model.game.features.process.types.reward.impl;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.process.types.reward.AbstractReward;
import de.zugpilot.cybercapture.model.game.features.process.types.reward.RewardResult;

public class PointsReward extends AbstractReward {

    private final double pointsAmount;

    public PointsReward(double chance, double pointsAmount) {
        super("Points", chance);
        this.pointsAmount = pointsAmount;
    }


    @Override
    public void handleReward(Computer computer) {
        if(handleChance() == RewardResult.REWARDED){
            computer.getTeam().addPoints(pointsAmount);
        }else{
            computer.getTeam().subtractPoints(pointsAmount);
        }
    }

}
