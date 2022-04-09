package de.zugpilot.cybercapture.model.game.features.process.types.generating;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class GenerationUI extends SubUI {
    public GenerationUI(CyberGame cyberGame, UI parent) {
        super(cyberGame, "§aProzessoren", 3, parent);
    }

    public void build(Player opened) {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        List<AbstractCyberProcess> generatingList = this.getCyberGame().getCyberProcessRegistry().getCyberProcessListByType(CyberProcessType.GENERATING);
        int i = 10;
        Optional<CyberPlayer> optional = getCyberGame().getPlayerRegistry().getCyberPlayer(opened.getUniqueId());
        if(optional.isEmpty())return;
        CyberPlayer cyberPlayer = optional.get();
        if(cyberPlayer.getTeam().isEmpty())return;
        for (AbstractCyberProcess process : generatingList) {
            GeneratingProcess generatingProcess = (GeneratingProcess) process;
            this.addElement(i, new ClickableUIElement(generatingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                generatingProcess.setSource(Optional.of(cyberPlayer));
                cyberPlayer.getTeam().get().getComputer().runProcess(cyberPlayer.getTeam().get().getComputer(), generatingProcess);
            }));
            ++i;
        }
        super.build();
    }
}
