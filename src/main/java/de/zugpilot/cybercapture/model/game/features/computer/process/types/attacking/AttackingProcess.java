package de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking;

import de.zugpilot.cybercapture.model.game.features.computer.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.computer.process.CyberProcessType;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public abstract class AttackingProcess extends AbstractCyberProcess {

    private final AttackVector attackVector;
    private final double processDamage;

    public AttackingProcess(AttackVector attackVector, String coloredProcessName, double processCost, long processDuration, Material processIcon, double processDamage, String... processDescription) {
        super(CyberProcessType.ATTACKING, coloredProcessName, processCost, processDuration, processIcon, processDescription);
        this.attackVector = attackVector;
        this.processDamage = processDamage;
    }

}
