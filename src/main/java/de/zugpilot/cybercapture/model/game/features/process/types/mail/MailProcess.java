package de.zugpilot.cybercapture.model.game.features.process.types.mail;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.process.AbstractCyberProcess;
import de.zugpilot.cybercapture.model.game.features.process.CyberProcessCategory;
import de.zugpilot.cybercapture.model.game.features.process.types.reward.impl.PointsReward;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MailProcess extends AbstractCyberProcess {

    private static final long MAIL_DELAY = 60 * 1000; //1 minute of delay
    private long lastMailAdded;
    private final List<Mail> inbox;
    public MailProcess() {
        super(CyberProcessCategory.MAIL, "§6Mail-Process", 0, -1, Material.BOOK, "usual mail client");
        this.inbox = new ArrayList<>();
        this.lastMailAdded = System.currentTimeMillis();
    }


    @Override
    public boolean tick(Computer computer) {
        if(System.currentTimeMillis() - lastMailAdded > MAIL_DELAY){
            this.lastMailAdded = System.currentTimeMillis();

        }
        return super.tick(computer);
    }
}
