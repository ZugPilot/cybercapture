package de.zugpilot.cybercapture.model.game.features.process.types.attacking;

import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public abstract class AttackingProcess extends AbstractCyberProcess {

    private final AttackVector attackVector;

    public AttackingProcess(AttackVector attackVector, String coloredProcessName, double processCost, long processDuration, Material processIcon, String... processDescription) {
        super(CyberProcessType.ATTACKING, coloredProcessName, processCost, processDuration, processIcon, processDescription);
        this.attackVector = attackVector;
    }

}
