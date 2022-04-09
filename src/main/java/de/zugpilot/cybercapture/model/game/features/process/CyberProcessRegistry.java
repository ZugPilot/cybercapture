package de.zugpilot.cybercapture.model.game.features.process;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl.Keylogger;
import de.zugpilot.cybercapture.model.game.features.process.types.attacking.impl.Malware;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.impl.AMDRyzen3;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.impl.AMDRyzen5;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.impl.AMDRyzen7;
import de.zugpilot.cybercapture.model.game.features.process.types.generating.impl.AMDRyzen9;
import de.zugpilot.cybercapture.model.game.features.process.types.protecting.impl.Avira;

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
        addProcess(new Keylogger());
        //PROTECTING
        addProcess(new Avira());
        //GENERATING
        addProcess(new AMDRyzen3());
        addProcess(new AMDRyzen5());
        addProcess(new AMDRyzen7());
        addProcess(new AMDRyzen9());
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
