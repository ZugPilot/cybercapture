package de.zugpilot.cybercapture.model.game.features.process.types.generating;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.ComputerSubUI;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessEnum;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class GenerationUI extends ComputerSubUI {

    public GenerationUI(CyberGame cyberGame, Computer computer, UI parent) {
        super(cyberGame, computer, parent);
    }

    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        List<CyberProcessEnum> generatingList = CyberProcessEnum.getByCategory(CyberProcessCategory.GENERATING);
        int i = 10;
        for (CyberProcessEnum process : generatingList) {
            GeneratingProcess generatingProcess = (GeneratingProcess) process.getCyberProcess().clone();
            //Add dynamic elements so that we can have a "you can't buy this" thingy in the lore when you can't buy it
            //I was too tired to do this in the night
            this.addElement(i, new ClickableUIElement(generatingProcess.getProcessIcon(), (player, slot, itemStack) -> {
                generatingProcess.setSource(Optional.of(getComputer().getTeam()));
                player.sendMessage(getComputer().runProcess(getComputer(), generatingProcess));
            }));
            ++i;
        }
        super.build();
    }
}
