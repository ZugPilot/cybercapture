package de.zugpilot.cybercapture.model.game.features;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import lombok.Getter;

@Getter
public abstract class ComputerSubUI extends SubUI {

    private final Computer computer;
    public ComputerSubUI(CyberGame cyberGame, Computer computer, UI parent) {
        super(cyberGame, parent);
        this.computer = computer;
    }

}
