package de.zugpilot.cybercapture.model.game.features.computer;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.computer.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.computer.process.CyberProcess;
import de.zugpilot.cybercapture.model.game.features.computer.process.CyberProcessType;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.protecting.ProtectingProcess;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Computer {

    private final ComputerUI computerUI;
    private final CyberTeam team;
    private final List<AbstractCyberProcess> processList;
    private double health;

    public Computer(CyberGame cyberGame, CyberTeam team) {
        this.computerUI = new ComputerUI(cyberGame, this);
        this.team = team;
        this.processList = new LinkedList<>();
        this.health = 1000;
    }

    public void tickComputer(){
        for(AbstractCyberProcess cyberProcess : getProcessList()){
            if(cyberProcess.tick(this)){
                this.processList.remove(cyberProcess);
            }
        }
    }

    public boolean attemptRunProcess(CyberPlayer sourcePlayer, CyberTeam sourceTeam, AbstractCyberProcess cyberProcess){
        if(sourceTeam.getPoints() < cyberProcess.getProcessCost()){
            sourcePlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDir fehlen Points um " + cyberProcess.getColoredProcessName() + " §czu starten!");
            return false;
        }
        if(cyberProcess.getCyberProcessType() == CyberProcessType.ATTACKING){
            if(sourceTeam.getTeamIdentifier().equals(getTeam().getTeamIdentifier())){
                sourcePlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDu kannst dein eigenes Team nicht angreifen!");
                return false;
            }
            if(handleProtection() == ProtectionResponse.FILTERED){
                getTeam().teamMessage(CyberConstants.PREFIX + " §cAttacke von der IP-Addresse " + sourceTeam.getColoredTeamName() + " §cwurde gefiltert!");
                getTeam().teamMessage(CyberConstants.PREFIX + " §cAngriff§8: §e" + cyberProcess.getColoredProcessName());
                return true; //This is done, so the attacker can't know if their attack was filtered or not
                             //Adds a bit more spice into the mix
            }
        }
        if(cyberProcess.getCyberProcessType() == CyberProcessType.PROTECTING){
            if(getProtectingProcess().isPresent()){
                sourcePlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDein Team hat bereits eine Antivirus Software!");
                return false;
            }
            sourcePlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §aErfolgreich " + cyberProcess.getColoredProcessName() + " §agekauft und gestartet.");
            sourcePlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §aLaufzeit: " + cyberProcess.getProcessDuration() + " Sekunden");
        }
        this.processList.add(cyberProcess);
        //I might not need to tick the process when added, idk if it matters much gameplay wise
        cyberProcess.tick(this);
        return true;
    }

    public Optional<ProtectingProcess> getProtectingProcess(){
        for(AbstractCyberProcess process : getProcessList()){
            if(process.getCyberProcessType() == CyberProcessType.PROTECTING){
                return Optional.of((ProtectingProcess) process);
            }
        }
        return Optional.empty();
    }

    /*
    TODO: This protection logic is untested, needs testing
     */

    public ProtectionResponse handleProtection(){
        Optional<ProtectingProcess> optional = getProtectingProcess();
        if(optional.isPresent()){
            ProtectingProcess protectingProcess = optional.get();
            double chance = Math.random();
            if(chance < protectingProcess.getProtectionChance() / 100){
                return ProtectionResponse.FILTERED;
            }
        }
        return ProtectionResponse.NOT_FILTERED;
    }

    private enum ProtectionResponse{
        FILTERED,
        NOT_FILTERED
    }

}
