package de.zugpilot.cybercapture.model.game.features.process.types.protecting;

import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class ProtectingProcess extends AbstractCyberProcess {

    private final double protectionChance;

    public ProtectingProcess(String coloredProcessName, double processCost, long processDuration, double protectionChance, Material processIcon, String... processDescription) {
        super(CyberProcessCategory.PROTECTING, coloredProcessName, processCost, processDuration, processIcon, processDescription);
        this.protectionChance = protectionChance;
    }

}
