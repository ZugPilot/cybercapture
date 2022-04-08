package de.zugpilot.cybercapture.model.game.features.computer;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking.ui.ComputerAttackUI;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.protecting.ProtectionUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.UIElement;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

public class ComputerUI extends UI {


    private final Computer computer;
    private final ComputerAttackUI computerAttackUI;
    private final ProtectionUI protectionUI;

    public ComputerUI(CyberGame cyberGame, Computer computer) {
        super(cyberGame, "§cComputer-Desktop", 6);
        this.computer = computer;
        this.computerAttackUI = new ComputerAttackUI(cyberGame,this);
        this.protectionUI = new ProtectionUI(cyberGame, this);
    }

    public void build() {
        fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        addElement(4, new UIElement(new ItemBuilder(Material.GRAY_DYE).displayName("§7Computer-Stats:").setLore("§7Leben: " + computer.getHealth(), "§7Points: " + computer.getTeam().getPoints()).build()));
        addElement(10, new ClickableUIElement(new ItemBuilder(Material.IRON_SWORD).displayName("§cGreife einen Gegner an!").setLore("§7Klicke um das Angriffsfenster zu öffnen").build(), (player, slot, itemStack) -> {
            computerAttackUI.build();
            computerAttackUI.open(player);
        }));
        addElement(13, new ClickableUIElement(new ItemBuilder(Material.ANVIL).displayName("§cRepariere deinen Computer").setLore("§7Klicke um das Reperatur-Menü zu öffnen").build(), (player, slot, itemStack) -> {
            //OPEN SUB UI
        }));
        addElement(16, new ClickableUIElement(new ItemBuilder(Material.DAYLIGHT_DETECTOR).displayName("§bProzessoren").setLore("§7Klicke um das Prozessor-Menü zu öffnen").build(), (player, slot, itemStack) -> {
            //OPEN SUB UI
        }));
        addElement(28, new ClickableUIElement(new ItemBuilder(Material.LEATHER_CHESTPLATE).displayName("§aAntiVirus-Software").setLore("§7Klicke um das Antiviren-Menü zu öffnen").build(), (player, slot, itemStack) -> {
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
