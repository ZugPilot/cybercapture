package de.zugpilot.cybercapture.model.game.features.process.types.mail;

import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.features.process.types.reward.AbstractReward;
import lombok.Getter;

@Getter
public class Mail {

    private final AbstractReward reward;
    private final String title;
    private final String[] content;

    public Mail(AbstractReward reward, String title, String... content){
        this.reward = reward;
        this.title = title;
        this.content = content;
    }

    public void open(Computer computer){
        reward.handleReward(computer);
    }

}
