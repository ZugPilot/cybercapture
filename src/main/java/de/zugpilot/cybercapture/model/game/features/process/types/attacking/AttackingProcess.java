package de.zugpilot.cybercapture.model.game.features.process.types.attacking;

import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class AttackingProcess extends AbstractCyberProcess {

    private final AttackCategory attackCategory;

    public AttackingProcess(AttackCategory attackCategory, String coloredProcessName, double processCost, long processDuration, Material processIcon, String... processDescription) {
        super(CyberProcessCategory.ATTACKING, coloredProcessName, processCost, processDuration, processIcon, processDescription);
        this.attackCategory = attackCategory;
    }

}
