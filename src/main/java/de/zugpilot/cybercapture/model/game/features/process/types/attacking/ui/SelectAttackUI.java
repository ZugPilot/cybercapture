package de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackVector;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.List;

public class SelectAttackUI extends SubUI
{
    private AttackVector selectedVector;

    public SelectAttackUI(final CyberGame cyberGame, final String title, final int rows, final UI parent) {
        super(cyberGame, title, rows, parent);
    }

    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        List<AbstractCyberProcess> attackingProcessList = this.getCyberGame().getCyberProcessRegistry().getCyberProcessListByType(CyberProcessType.ATTACKING);
        for (AbstractCyberProcess process : attackingProcessList) {
            AttackingProcess attackingProcess = (AttackingProcess)process;
            if(selectedVector == attackingProcess.getAttackVector()){
                this.addElement(i, new ClickableUIElement(attackingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                    SelectTeamToAttackUI teamToAttackUI = new SelectTeamToAttackUI(getCyberGame(), attackingProcess, "§cWelches Team?", 3, this);
                    teamToAttackUI.build();
                    teamToAttackUI.open(player);
                    return;
                }));
                ++i;
            }
        }
        super.build();
    }

    public void setSelectedVector(AttackVector selectedVector) {
        this.selectedVector = selectedVector;
    }

    public AttackVector getSelectedVector() {
        return selectedVector;
    }
}
