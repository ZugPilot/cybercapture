package de.zugpilot.cybercapture.model.game.features;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui.ComputerAttackUI;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.GenerationUI;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.ProtectionUI;
import de.zugpilot.cybercapture.ui.element.UIElement;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

public class MainUI extends ComputerUI {

    private final ComputerAttackUI computerAttackUI;
    private final ProtectionUI protectionUI;
    private final GenerationUI generationUI;

    public MainUI(CyberGame cyberGame, Computer computer) {
        super(cyberGame, computer);
        this.computerAttackUI = new ComputerAttackUI(getCyberGame(), getComputer(), this);
        this.protectionUI = new ProtectionUI(getCyberGame(), getComputer(), this);
        this.generationUI = new GenerationUI(getCyberGame(), getComputer(), this);
    }

    public void build() {
        fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        addElement(4, new UIElement(new ItemBuilder(Material.GRAY_DYE).displayName("§7Computer-Stats:").setLore(
                "§7Leben: " + getComputer().getHealth(),
                "§7Points: " + getComputer().getTeam().getPoints(),
                "§7Antiviren Software: " + (getComputer().getProtectingProcess().isEmpty() ? "Nicht gefunden" : getComputer().getProtectingProcess().get().getColoredProcessName()),
                "§7CPU: " + (getComputer().getGeneratingProcess().isEmpty() ? "Nicht gefunden" : getComputer().getGeneratingProcess().get().getColoredProcessName())).build()));
        addElement(10, new ClickableUIElement(new ItemBuilder(Material.IRON_SWORD).displayName("§cGreife einen Gegner an!").setLore("§7Klicke um das Angriffsfenster zu öffnen").build(), (player, slot, itemStack) -> {
            computerAttackUI.setup("§cWelche Angriffsart?", 3);
            computerAttackUI.build();
            computerAttackUI.open(player);
        }));
        addElement(13, new ClickableUIElement(new ItemBuilder(Material.ANVIL).displayName("§cRepariere deinen Computer").setLore("§7Klicke um das Reperatur-Menü zu öffnen").build(), (player, slot, itemStack) -> {
            //OPEN SUB UI
        }));
        addElement(16, new ClickableUIElement(new ItemBuilder(Material.DAYLIGHT_DETECTOR).displayName("§bProzessoren").setLore("§7Klicke um das Prozessor-Menü zu öffnen").build(), (player, slot, itemStack) -> {
            generationUI.setup("§bProzessoren", 3);
            generationUI.build();
            generationUI.open(player);
        }));
        addElement(28, new ClickableUIElement(new ItemBuilder(Material.LEATHER_CHESTPLATE).displayName("§aAntiVirus-Software").setLore("§7Klicke um das Antiviren-Menü zu öffnen").build(), (player, slot, itemStack) -> {
            protectionUI.setup("§5Antiviren Software", 3);
            protectionUI.build();
            protectionUI.open(player);
        }));
        addElement(31, new ClickableUIElement(new ItemBuilder(Material.LEATHER_CHESTPLATE).displayName("§7Zusätzliche Programme").setLore("§7Klicke um dir zusätzliche Programme anzuschauen").build(), (player, slot, itemStack) -> {
            //OPEN SUB UI
        }));
        addElement(53, new ClickableUIElement(new ItemBuilder(Material.BOOK).displayName("§8Papierkorb").setLore("§7Vielleicht findest du ja was nützliches?").build(), (player, slot, itemStack) -> {
            //OPEN SUB UI
        }));
        super.build();
    }

}
