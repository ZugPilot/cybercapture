package de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.ComputerSubUI;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackCategory;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

public class ComputerAttackUI extends ComputerSubUI
{


    public ComputerAttackUI(CyberGame cyberGame, Computer computer, UI parent) {
        super(cyberGame, computer, parent);
    }

    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        for (final AttackCategory vector : AttackCategory.values()) {
            this.addElement(i, new ClickableUIElement(new ItemBuilder(vector.getAttackVectorIcon()).displayName(vector.getColoredAttackVectorName()).setLore(vector.getAttackVectorDescription()).build(), (player, slot, itemStack) -> {
                SelectAttackUI selectAttackUI = new SelectAttackUI(getCyberGame(), getComputer(), vector, this);
                selectAttackUI.setSelectedVector(vector);
                selectAttackUI.setup("§cWelcher Angriff?", 3);
                selectAttackUI.build();
                selectAttackUI.open(player);
                return;
            }));
            i += 2;
        }
        super.build();
    }
}
