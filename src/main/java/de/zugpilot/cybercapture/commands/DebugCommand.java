package de.zugpilot.cybercapture.commands;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {

    private final CyberGame cyberGame;

    public DebugCommand(CyberGame cyberGame){
        this.cyberGame = cyberGame;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!commandSender.hasPermission(CyberConstants.PERMISSION_PREFIX + "debug")){
            commandSender.sendMessage(CyberConstants.NO_PERMISSIONS);
            return false;
        }
        if(commandSender instanceof Player player){
            if(cyberGame.getPlayerRegistry().getCyberPlayer(player.getUniqueId()).isPresent()){
                CyberPlayer cyberPlayer = cyberGame.getPlayerRegistry().getCyberPlayer(player.getUniqueId()).get();
                if(cyberPlayer.getTeam().isPresent()){
                    CyberTeam cyberTeam = cyberPlayer.getTeam().get();
                    cyberTeam.getComputer().getMainUI().setup(cyberTeam.getColoredTeamName() + " §7Computer", 6);
                    cyberTeam.getComputer().getMainUI().build();
                    cyberTeam.getComputer().getMainUI().open(player);
                }
            }
        }
        commandSender.sendMessage("§aDebug executed!");
        return false;
    }
}
