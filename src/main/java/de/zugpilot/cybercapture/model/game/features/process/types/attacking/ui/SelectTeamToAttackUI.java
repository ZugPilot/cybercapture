package de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.ComputerSubUI;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.Optional;

public class SelectTeamToAttackUI extends ComputerSubUI
{
    private final AttackingProcess attackingProcess;

    public SelectTeamToAttackUI(CyberGame cyberGame, Computer computer, AttackingProcess attackingProcess, UI parent) {
        super(cyberGame, computer, parent);
        this.attackingProcess = attackingProcess;
    }


    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        for (final CyberTeam team : this.getCyberGame().getTeamRegistry().getTeams()) {
            this.addElement(i, new ClickableUIElement(team.getTeamIcon(), (player, slot, itemStack1) -> {
                attackingProcess.setSource(Optional.of(getComputer().getTeam()));
                getComputer().runProcess(team.getComputer(), attackingProcess);
            }));
            ++i;
        }
        super.build();
    }
}
