package de.zugpilot.cybercapture.model.game.features.computer.process.types.protecting.impl;

import de.zugpilot.cybercapture.model.game.features.computer.process.types.protecting.ProtectingProcess;
import org.bukkit.Material;

public class Avast extends ProtectingProcess {
    public Avast() {
        super("§aAvira Antivirus", 250, 100, 25, Material.LEATHER_CHESTPLATE, "§7Ein Klassiker in der Welt der Antivren-Software", "§7Leider vor allem bekannt dafür, nicht besonders gut zu sein");
    }
}
