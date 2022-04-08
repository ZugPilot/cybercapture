package de.zugpilot.cybercapture.model.game.features.computer.process;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking.impl.Malware;
import de.zugpilot.cybercapture.model.game.features.computer.process.types.protecting.impl.Avast;

import java.util.*;

public class CyberProcessRegistry {

    private final CyberGame cyberGame;
    private final Map<CyberProcessType, List<AbstractCyberProcess>> registeredProcesses;

    public CyberProcessRegistry(CyberGame cyberGame){
        this.cyberGame = cyberGame;
        this.registeredProcesses = new HashMap<>();
        registerProcesses();
    }

    public List<AbstractCyberProcess> getCyberProcessListByType(CyberProcessType type){
        if(registeredProcesses.containsKey(type)){
            return registeredProcesses.get(type);
        }
        return Collections.emptyList();
    }

    public void registerProcesses(){
        //ATTACKING
        addProcess(new Malware());
        //PROTECTING
        addProcess(new Avast());
    }

    public void addProcess(AbstractCyberProcess cyberProcess){
        if(!this.registeredProcesses.containsKey(cyberProcess.getCyberProcessType())){
            List<AbstractCyberProcess> processList = new ArrayList<>();
            processList.add(cyberProcess);
            this.registeredProcesses.put(cyberProcess.getCyberProcessType(), processList);
        }else{
            this.registeredProcesses.get(cyberProcess.getCyberProcessType()).add(cyberProcess);
        }
    }



}
