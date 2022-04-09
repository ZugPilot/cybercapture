package de.zugpilot.cybercapture.model.game.features.process;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class AbstractCyberProcess implements CyberProcess {

    private final CyberProcessType cyberProcessType;
    private final String processName;
    private final String coloredProcessName;
    private final double processCost;
    private final long processDuration;
    private ItemStack processIcon;
    private final Material processIconMaterial;
    private final List<String> processDescription;

    private long processStartTimeStamp;
    private Optional<CyberPlayer> source;

    public AbstractCyberProcess(CyberProcessType cyberProcessType, String coloredProcessName, double processCost, long processDuration, Material processIcon, String... processDescription) {
        this.cyberProcessType = cyberProcessType;
        this.processName = ChatColor.stripColor(coloredProcessName);
        this.coloredProcessName = coloredProcessName;
        this.processCost = processCost;
        this.processDuration = processDuration;

        this.processDescription = new ArrayList<>();
        this.processDescription.add("§7Price§8: §e" + processCost);
        if(processDuration == -1){
            this.processDescription.add("§7Duration§8: §enever ending");
        }else{
            this.processDescription.add("§7Duration§8: §e" + processDuration + " Sekunden");
        }
        this.processDescription.add("");
        this.processDescription.addAll(Arrays.asList(processDescription));

        String[] lore = new String[this.processDescription.size()];
        this.processDescription.toArray(lore);

        this.processIconMaterial = processIcon;
        this.processIcon = new ItemBuilder(processIcon).displayName(coloredProcessName).setLore(lore).build();

        this.source = Optional.empty();
    }

    @Override
    public boolean tick(Computer computer) {
        return System.currentTimeMillis() > processDuration * 1000;
    }

    /*
    Call when something is added to the processDescription list
     */

    public void updateDescription(){
        String[] lore = new String[this.processDescription.size()];
        this.processDescription.toArray(lore);
        this.processIcon = new ItemBuilder(this.processIconMaterial).displayName(this.coloredProcessName).setLore(lore).build();
    }

}
