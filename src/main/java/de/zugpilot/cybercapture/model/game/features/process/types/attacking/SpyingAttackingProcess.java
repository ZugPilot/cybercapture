package de.zugpilot.cybercapture.model.game.features.process.types.attacking;

import org.bukkit.Material;

public class SpyingAttackingProcess extends AttackingProcess {

    public SpyingAttackingProcess(AttackVector attackVector, String coloredProcessName, double processCost, long processDuration, Material processIcon, String... processDescription) {
        super(attackVector, coloredProcessName, processCost, processDuration, processIcon, processDescription);
    }

}
