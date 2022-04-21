package de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl;

import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackCategory;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import org.bukkit.Material;

public class SpyingAttackingProcess extends AttackingProcess {

    public SpyingAttackingProcess(AttackCategory attackCategory, String coloredProcessName, double processCost, long processDuration, Material processIcon, String... processDescription) {
        super(attackCategory, coloredProcessName, processCost, processDuration, processIcon, processDescription);
    }

}
