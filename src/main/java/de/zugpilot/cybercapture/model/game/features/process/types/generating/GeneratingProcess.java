package de.zugpilot.cybercapture.model.game.features.process.types.generating;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

@Getter
public class GeneratingProcess extends AbstractCyberProcess {

    private final long tier;
    private final double pointsPerTick;

    public GeneratingProcess(String coloredProcessName, double processCost, double pointsPerTick, long tier, Material processIcon, String... processDescription) {
        super(CyberProcessType.GENERATING, coloredProcessName, processCost, -1, processIcon, processDescription);
        this.pointsPerTick = pointsPerTick;
        this.tier = tier;
    }

    @Override
    public boolean tick(Computer computer) {
        computer.getTeam().setPoints(computer.getTeam().getPoints() + pointsPerTick);
        return super.tick(computer);
    }

}
