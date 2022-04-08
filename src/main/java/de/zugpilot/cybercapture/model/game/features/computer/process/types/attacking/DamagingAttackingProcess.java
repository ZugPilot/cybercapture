package de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking;

import de.zugpilot.cybercapture.model.game.features.computer.Computer;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public abstract class DamagingAttackingProcess extends AttackingProcess {
    public DamagingAttackingProcess(AttackVector attackVector, String coloredProcessName, double processCost, long processDuration, Material processIcon, double processDamage, String... processDescription) {
        super(attackVector, coloredProcessName, processCost, processDuration, processIcon, processDamage, processDescription);
    }

    @Override
    public boolean tick(Computer computer) {
        if(computer.getHealth() - getProcessDamage() > 0){
            computer.setHealth(computer.getHealth() - getProcessDamage());
        }else{
            computer.setHealth(0);
        }
        return super.tick(computer);
    }
}
