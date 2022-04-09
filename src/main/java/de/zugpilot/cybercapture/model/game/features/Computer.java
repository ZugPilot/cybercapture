package de.zugpilot.cybercapture.model.game.features;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessType;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.SpyingAttackingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.GeneratingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.impl.AMDRyzen3;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.ProtectingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.impl.Avira;
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
        setup();
    }

    /*
    Give the player a default cpu and anti virus software
     */

    public void setup(){
        this.processList.add(new AMDRyzen3());
    }

    public void tickComputer(){
        for(AbstractCyberProcess cyberProcess : getProcessList()){
            if(cyberProcess.tick(this)){
                this.processList.remove(cyberProcess);
            }
        }
    }

    public void runProcess(Computer otherComputer, AbstractCyberProcess cyberProcess){
        Optional<CyberPlayer> sourcePlayer = cyberProcess.getSource();
        if(cyberProcess.getCyberProcessType() == CyberProcessType.GENERATING){
            if(sameComputer(this, otherComputer)){
                if(getGeneratingProcess().isPresent()){
                    GeneratingProcess attempted = (GeneratingProcess)cyberProcess;
                    GeneratingProcess current = getGeneratingProcess().get();
                    if(current.getProcessName().equals(attempted.getProcessName())){
                        sourcePlayer.ifPresent(cyberPlayer -> cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDu besitzt diesen Prozessor bereits!"));
                        return;
                    }
                    if(attempted.getTier() - 1 != current.getTier()){
                        sourcePlayer.ifPresent(cyberPlayer -> cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDu musst ein Vorgängermodell besitzen, um diesem Prozessor zu kaufen!"));
                        return;
                    }
                    processList.remove(current);
                    sourcePlayer.ifPresent(cyberPlayer -> cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §aDu hast den Prozessor " + attempted.getColoredProcessName() + " §aerfolgreich gekauft."));
                }
            }
        }
        if(cyberProcess.getCyberProcessType() == CyberProcessType.PROTECTING){
            if(!sameComputer(this, otherComputer)){
                return;
            }
            if(getProtectingProcess().isPresent()){
                sourcePlayer.ifPresent(cyberPlayer -> cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cEs läuft bereits eine Antiviren Software auf diesem PC."));
                return;
            }
            getTeam().teamMessage(CyberConstants.PREFIX + " §aAntiviren Software " + cyberProcess.getColoredProcessName() + " §awurde erfolgreich gestartet!");
            getTeam().teamMessage(CyberConstants.PREFIX + " §aLaufdauer: " + cyberProcess.getProcessDuration() + " §aSekunden");
        }
        if(cyberProcess.getCyberProcessType() == CyberProcessType.ATTACKING){
            if(sameComputer(this, otherComputer)){
                sourcePlayer.ifPresent(cyberPlayer -> cyberPlayer.getPlayer().sendMessage(CyberConstants.PREFIX + " §cDu kannst dein eigenes Team nicht angreifen!"));
                return;
            }
            for(AbstractCyberProcess process : getProcessList()){
                if(process instanceof SpyingAttackingProcess spyingAttack){
                    if(spyingAttack.getSource().isPresent() && spyingAttack.getSource().get().getTeam().isPresent()){
                        spyingAttack.getSource().get().getTeam().get().teamMessage("§8[§5Keylogger§8] §c" + getTeam().getColoredTeamName() + " §8-> " + otherComputer.getTeam().getColoredTeamName());
                        spyingAttack.getSource().get().getTeam().get().teamMessage("§8[§5Keylogger§8] §cAngriff: " + cyberProcess.getColoredProcessName());
                    }
                }
            }
            if(otherComputer.handleProtection() == ProtectionResponse.FILTERED){
                otherComputer.getTeam().teamMessage(CyberConstants.PREFIX + " §cEin Angriff wurde gefiltert!");
                otherComputer.getTeam().teamMessage(CyberConstants.PREFIX + " §cAngriff:" + cyberProcess.getColoredProcessName());
                return;
            }
        }
        cyberProcess.tick(this);
        otherComputer.processList.add(cyberProcess);
    }

    public boolean sameComputer(Computer computer1, Computer computer2){
        return computer1.getTeam().getTeamIdentifier().equals(computer2.getTeam().getTeamIdentifier());
    }

    public Optional<ProtectingProcess> getProtectingProcess(){
        for(AbstractCyberProcess process : getProcessList()){
            if(process.getCyberProcessType() == CyberProcessType.PROTECTING){
                return Optional.of((ProtectingProcess) process);
            }
        }
        return Optional.empty();
    }

    public Optional<GeneratingProcess> getGeneratingProcess(){
        for(AbstractCyberProcess process : getProcessList()){
            if(process.getCyberProcessType() == CyberProcessType.GENERATING){
                return Optional.of((GeneratingProcess) process);
            }
        }
        return Optional.empty();
    }

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
