package de.zugpilot.cybercapture.model.game.features.process;

import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackCategory;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl.DamagingAttackingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.GeneratingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.ProtectingProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public enum CyberProcessEnum {

    ANTI_VIR(CyberProcessCategory.PROTECTING, new ProtectingProcess("AntiVir", 250, 1, 1, Material.DIAMOND_CHESTPLATE, "")),
    AVAST(CyberProcessCategory.PROTECTING, new ProtectingProcess("Avast", 350, 1, 1, Material.IRON_CHESTPLATE, "")),
    BIT_DEFENDER(CyberProcessCategory.PROTECTING, new ProtectingProcess("Bit-Defender", 400, 1, 1, Material.CHAINMAIL_CHESTPLATE, "")),
    NORTON(CyberProcessCategory.PROTECTING, new ProtectingProcess("Norton", 150, 1, 1, Material.LEATHER_CHESTPLATE, "")),

    AMD_RYZEN_3(CyberProcessCategory.GENERATING, new GeneratingProcess("Ryzen 3", 100, 1, 1, Material.DAYLIGHT_DETECTOR, "")),
    AMD_RYZEN_5(CyberProcessCategory.GENERATING, new GeneratingProcess("Ryzen 5", 250, 3, 2, Material.DAYLIGHT_DETECTOR, "")),
    AMD_RYZEN_7(CyberProcessCategory.GENERATING, new GeneratingProcess("Ryzen 7", 500, 5, 3, Material.DAYLIGHT_DETECTOR, "")),

    MALWARE(CyberProcessCategory.ATTACKING, new DamagingAttackingProcess(AttackCategory.MALWARE, "§cMalware", 20, 20, Material.IRON_SWORD, 1, "")),
    ;

    private final CyberProcessCategory cyberProcessCategory;
    private final AbstractCyberProcess cyberProcess;

    public static List<CyberProcessEnum> getByCategory(CyberProcessCategory cyberProcessCategory){
        List<CyberProcessEnum> enumList = new ArrayList<>();
        for(CyberProcessEnum enumCyberProcess : CyberProcessEnum.values()){
            if(enumCyberProcess.getCyberProcessCategory() == cyberProcessCategory){
                enumList.add(enumCyberProcess);
            }
        }
        return enumList;
    }

}
