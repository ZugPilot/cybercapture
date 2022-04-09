package de.zugpilot.cybercapture.model.game.features.process.types.protecting;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.List;
import java.util.Optional;

public class ProtectionUI extends SubUI {
    public ProtectionUI(CyberGame cyberGame, UI parent) {
        super(cyberGame, "§aAntiVirus Software", 3, parent);
    }

    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        List<AbstractCyberProcess> protectingProcessList = this.getCyberGame().getCyberProcessRegistry().getCyberProcessListByType(CyberProcessType.PROTECTING);
        int i = 10;
        for (AbstractCyberProcess process : protectingProcessList) {
            ProtectingProcess protectingProcess = (ProtectingProcess) process;
            this.addElement(i, new ClickableUIElement(protectingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                Optional<CyberPlayer> optional = getCyberGame().getPlayerRegistry().getCyberPlayer(player.getUniqueId());
                if(optional.isEmpty())return;
                CyberPlayer cyberPlayer = optional.get();
                if(cyberPlayer.getTeam().isEmpty())return;
                protectingProcess.setSource(Optional.of(cyberPlayer));
                cyberPlayer.getTeam().get().getComputer().runProcess(cyberPlayer.getTeam().get().getComputer(), protectingProcess);
            }));
            ++i;
        }
        super.build();
    }
}
