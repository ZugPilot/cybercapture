package de.zugpilot.cybercapture.model.game.team;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.ui.UI;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;

public class LobbySelectorUI extends UI {

    private final CyberPlayer cyberPlayer;

    public LobbySelectorUI(CyberGame cyberGame, CyberPlayer cyberPlayer) {
        super(cyberGame, "§aSelect your team", 3);
        this.cyberPlayer = cyberPlayer;
    }

    public void build() {
        fill(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 10;
        for(CyberTeam team : getCyberGame().getTeamRegistry().getTeams()){
            addElement(i, new ClickableUIElement(team.getTeamIcon(), (player, slot, itemStack1) -> {
                System.out.println(team.getTeamIcon().getType());
                if(team.isPlayer(cyberPlayer.getPlayer().getUniqueId())){
                    team.removePlayer(cyberPlayer);
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cYou left this team!");
                    return;
                }
                if(team.currentPlayerAmount() >= team.getMaxPlayers()){
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cThis team is already full!");
                    return;
                }
                if(cyberPlayer.getTeam().isPresent()){
                    CyberTeam current = cyberPlayer.getTeam().get();
                    current.removePlayer(cyberPlayer);
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §6Since you joined a new team, you were removed from your old team.");
                }
                team.addPlayer(cyberPlayer);
                cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §aYou are now part of this team.");
            }));
            i++;
        }
        super.build();
    }


    /*public void build() {
        fill(new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).displayName("§f").build());
        int i = 0;
        for(CyberTeam team : cyberGame.getTeamRegistry().getTeams()){
            this.addElement(i, new ClickableUIElement(team.getTeamIcon(), (slot, itemStack1) -> {
                if(team.isPlayer(cyberPlayer.getPlayer().getUniqueId())){
                    team.removePlayer(cyberPlayer);
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cYou left this team!");
                    return;
                }
                if(team.currentPlayerAmount() >= team.getMaxPlayers()){
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cThis team is already full!");
                    return;
                }
                if(cyberPlayer.getTeam().isPresent()){
                    CyberTeam current = cyberPlayer.getTeam().get();
                    current.removePlayer(cyberPlayer);
                    cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §6Since you joined a new team, you were removed from your old team.");
                }
                team.addPlayer(cyberPlayer);
                cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §aYou are now part of this team.");
            }));
            i++;
        }
        super.build();
    }
     */
}
