package de.zugpilot.cybercapture.model.game.features.process.types.protecting;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.ComputerSubUI;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessEnum;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.List;
import java.util.Optional;

public class ProtectionUI extends ComputerSubUI {


    public ProtectionUI(CyberGame cyberGame, Computer computer, UI parent) {
        super(cyberGame, computer, parent);
    }

    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        List<CyberProcessEnum> protectingProcessList = CyberProcessEnum.getByCategory(CyberProcessCategory.PROTECTING);
        int i = 10;
        for (CyberProcessEnum cyberProcessEnum : protectingProcessList) {
            ProtectingProcess protectingProcess = (ProtectingProcess) cyberProcessEnum.getCyberProcess().clone();
            this.addElement(i, new ClickableUIElement(protectingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                protectingProcess.setSource(Optional.of(getComputer().getTeam()));
                player.sendMessage(getComputer().runProcess(getComputer(), protectingProcess));
            }));
            ++i;
        }
        super.build();
    }
}
