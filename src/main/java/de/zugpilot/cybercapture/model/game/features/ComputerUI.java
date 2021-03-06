package de.zugpilot.cybercapture.model.game.features;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.UI;
import lombok.Getter;

@Getter
public abstract class ComputerUI extends UI {

    private final Computer computer;
    public ComputerUI(CyberGame cyberGame, Computer computer) {
        super(cyberGame);
        this.computer = computer;
    }

}
