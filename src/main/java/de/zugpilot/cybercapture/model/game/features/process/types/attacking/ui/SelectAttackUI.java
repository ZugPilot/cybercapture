package de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.ComputerSubUI;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessEnum;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackCategory;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.List;

public class SelectAttackUI extends ComputerSubUI
{
    private AttackCategory selectedVector;

    public SelectAttackUI(CyberGame cyberGame, Computer computer, AttackCategory selectedVector, UI parent) {
        super(cyberGame, computer, parent);
        this.selectedVector = selectedVector;
    }


    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        List<CyberProcessEnum> attackingProcessList = CyberProcessEnum.getByCategory(CyberProcessCategory.ATTACKING);
        for (CyberProcessEnum process : attackingProcessList) {
            AttackingProcess attackingProcess = (AttackingProcess)process.getCyberProcess().clone();
            if(selectedVector == attackingProcess.getAttackCategory()){
                this.addElement(i, new ClickableUIElement(attackingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                    SelectTeamToAttackUI teamToAttackUI = new SelectTeamToAttackUI(getCyberGame(), getComputer(), attackingProcess, this);
                    teamToAttackUI.setup("§cWen angreifen?", 3);
                    teamToAttackUI.build();
                    teamToAttackUI.open(player);
                }));
                ++i;
            }
        }
        super.build();
    }

    public void setSelectedVector(AttackCategory selectedVector) {
        this.selectedVector = selectedVector;
    }

    public AttackCategory getSelectedVector() {
        return selectedVector;
    }
}
