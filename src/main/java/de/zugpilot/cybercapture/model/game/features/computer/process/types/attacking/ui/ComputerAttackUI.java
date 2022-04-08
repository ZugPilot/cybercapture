package de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking.AttackVector;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

public class ComputerAttackUI extends SubUI
{
    public ComputerAttackUI(final CyberGame cyberGame, final UI parent) {
        super(cyberGame, "§cAngriffs-Vektor", 3, parent);
    }

    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        for (final AttackVector vector : AttackVector.values()) {
            this.addElement(i, new ClickableUIElement(new ItemBuilder(vector.getAttackVectorIcon()).displayName(vector.getColoredAttackVectorName()).setLore(vector.getAttackVectorDescription()).build(), (player, slot, itemStack) -> {
                SelectAttackUI selectAttackUI = new SelectAttackUI(this.getCyberGame(), vector.getColoredAttackVectorName(), 3, this);
                selectAttackUI.setSelectedVector(vector);
                selectAttackUI.build();
                selectAttackUI.open(player);
                return;
            }));
            i += 2;
        }
        super.build();
    }
}
