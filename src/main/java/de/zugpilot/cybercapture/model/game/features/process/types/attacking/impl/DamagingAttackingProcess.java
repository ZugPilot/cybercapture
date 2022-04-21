package de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackCategory;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public class DamagingAttackingProcess extends AttackingProcess {

    private final double processDamage;

    public DamagingAttackingProcess(AttackCategory attackCategory, String coloredProcessName, double processCost, long processDuration, Material processIcon, double processDamage, String... processDescription) {
        super(attackCategory, coloredProcessName, processCost, processDuration, processIcon, processDescription);
        this.processDamage = processDamage;
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
