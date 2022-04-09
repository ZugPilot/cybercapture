package de.zugpilot.cybercapture.model.game.features.process.types.attacking.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.AttackingProcess;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.SubUI;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

import java.util.Optional;

public class SelectTeamToAttackUI extends SubUI
{
    private final AttackingProcess attackingProcess;

    public SelectTeamToAttackUI(final CyberGame cyberGame, final AttackingProcess attackingProcess, final String title, final int rows, final UI parent) {
        super(cyberGame, title, rows, parent);
        this.attackingProcess = attackingProcess;
    }

    @Override
    public void build() {
        this.fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        for (final CyberTeam team : this.getCyberGame().getTeamRegistry().getTeams()) {
            this.addElement(i, new ClickableUIElement(team.getTeamIcon(), (player, slot, itemStack1) -> {
                Optional<CyberPlayer> optional = getCyberGame().getPlayerRegistry().getCyberPlayer(player.getUniqueId());
                if(optional.isEmpty())return;
                CyberPlayer cyberPlayer = optional.get();
                if(cyberPlayer.getTeam().isEmpty())return;
                attackingProcess.setSource(Optional.of(cyberPlayer));
                cyberPlayer.getTeam().get().getComputer().runProcess(team.getComputer(), attackingProcess);
            }));
            ++i;
        }
        super.build();
    }
}
