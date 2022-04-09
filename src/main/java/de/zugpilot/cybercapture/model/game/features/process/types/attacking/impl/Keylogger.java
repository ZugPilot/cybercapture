package de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl;

import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackVector;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.SpyingAttackingProcess;
import org.bukkit.Material;

public class Keylogger extends SpyingAttackingProcess {
    public Keylogger() {
        super(AttackVector.BACK_DOOR, "§bKey-Logger", 250, 60, Material.FISHING_ROD, "§7Erfahre was für Angriffe dein Gegner startet und gegen wen!");
    }
}
