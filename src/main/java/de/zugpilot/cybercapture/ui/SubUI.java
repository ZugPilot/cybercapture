package de.zugpilot.cybercapture.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Getter
public abstract class SubUI extends UI {

    private final UI parent;

    public SubUI(CyberGame cyberGame, UI parent) {
        super(cyberGame);
        this.parent = parent;
    }

    public void open(Player player) {
        this.parent.close(player);
        super.open(player);
    }

    public void build() {
        addElement(getOutput().getSize() - 1, new ClickableUIElement(new ItemBuilder(Material.RED_STAINED_GLASS_PANE).displayName("§cZurück").build(), (player, slot, itemStack) -> {
            this.close(player);
            parent.build();
            parent.open(player);
        }));
        super.build();
    }

}
