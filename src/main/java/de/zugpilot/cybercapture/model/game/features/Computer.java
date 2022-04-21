package de.zugpilot.cybercapture.model.game.features;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessEnum;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.GeneratingProcess;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.ProtectingProcess;
import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Computer {

    private final MainUI mainUI;
    private final CyberTeam team;
    private final List<AbstractCyberProcess> processList;
    private double health;

    public Computer(CyberGame cyberGame, CyberTeam team) {
        this.mainUI = new MainUI(cyberGame, this);
        this.team = team;
        this.processList = new LinkedList<>();
        this.health = 1000;
        runDefaultProcesses();
    }

    /*
    Run the necessary default processes like a beginner CPU, the mail process,
    the repairing process and maybe a default Antivirus later...
     */

    public void runDefaultProcesses(){
        this.processList.add(CyberProcessEnum.AMD_RYZEN_3.getCyberProcess().clone());
    }

    public void tickComputer(){
        for(AbstractCyberProcess cyberProcess : getProcessList()){
            if(cyberProcess.tick(this)){
                this.processList.remove(cyberProcess);
            }
        }
    }

    public String runProcess(Computer otherComputer, AbstractCyberProcess cyberProcess){
        String output = "";
        if(cyberProcess.getCyberProcessCategory() == CyberProcessCategory.GENERATING){
            if(sameComputer(this, otherComputer)){
                if(getGeneratingProcess().isPresent()){
                    GeneratingProcess attempted = (GeneratingProcess)cyberProcess;
                    GeneratingProcess current = getGeneratingProcess().get();
                    if(current.getProcessName().equals(attempted.getProcessName())){
                        return "§cEs wird bereits dieser Prozessor verwendet.";
                    }
                    if(attempted.getTier() - 1 != current.getTier()){
                        return "§cBitte kaufe zu erst die Vorgängermodelle!";
                    }
                    processList.remove(current);
                    output = "§aDer Prozessor " + cyberProcess.getColoredProcessName() + " §awurde erfolgreich gekauft und ausgerüstet.";
                }
            }
        }
        if(cyberProcess.getCyberProcessCategory() == CyberProcessCategory.PROTECTING){
            if(!sameComputer(this, otherComputer)){
                return "§cDiese Nachricht sollte gar nicht erscheinen! Bitte bei ZugPilot (Dev) mit dem Hinweis 'PROT_SAME_COMPUTER' melden";
            }
            if(getProtectingProcess().isPresent()){
                return "§cEs läuft bereits eine Antiviren Software auf diesem PC!";
            }
            getTeam().teamMessage(CyberConstants.PREFIX + " §aAntiviren Software " + cyberProcess.getColoredProcessName() + " §awurde erfolgreich gestartet!");
            getTeam().teamMessage(CyberConstants.PREFIX + " §aLaufdauer: " + cyberProcess.getProcessDuration() + " §aSekunden");
        }
        if(cyberProcess.getCyberProcessCategory() == CyberProcessCategory.ATTACKING){
            if(sameComputer(this, otherComputer)){
                return "§cDu kannst dein eigenes Team nicht angreifen!";
            }
            for(AbstractCyberProcess process : getProcessList()){
                if(process.getProcessName().equalsIgnoreCase("keylogger")){
                    if(process.getSource().isPresent()){
                        process.getSource().get().teamMessage("§8[§5Keylogger§8] §c" + getTeam().getColoredTeamName() + " §8-> " + otherComputer.getTeam().getColoredTeamName());
                        process.getSource().get().teamMessage("§8[§5Keylogger§8] §cAngriff: " + cyberProcess.getColoredProcessName());
                    }
                }
            }
            if(otherComputer.handleProtection() == ProtectionResponse.FILTERED){
                otherComputer.getTeam().teamMessage(CyberConstants.PREFIX + " §cEin Angriff wurde gefiltert!");
                otherComputer.getTeam().teamMessage(CyberConstants.PREFIX + " §cAngriff:" + cyberProcess.getColoredProcessName());
                return "";
            }
        }
        cyberProcess.tick(this);
        otherComputer.processList.add(cyberProcess);
        return output;
    }

    public boolean sameComputer(Computer computer1, Computer computer2){
        return computer1.getTeam().getTeamIdentifier().equals(computer2.getTeam().getTeamIdentifier());
    }

    public Optional<ProtectingProcess> getProtectingProcess(){
        for(AbstractCyberProcess process : getProcessList()){
            if(process.getCyberProcessCategory() == CyberProcessCategory.PROTECTING){
                return Optional.of((ProtectingProcess) process);
            }
        }
        return Optional.empty();
    }

    public Optional<GeneratingProcess> getGeneratingProcess(){
        for(AbstractCyberProcess process : getProcessList()){
            if(process.getCyberProcessCategory() == CyberProcessCategory.GENERATING){
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
