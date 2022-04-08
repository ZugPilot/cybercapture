package de.zugpilot.cybercapture.model.game;

import de.zugpilot.cybercapture.CyberPlugin;
import de.zugpilot.cybercapture.commands.DebugCommand;
import de.zugpilot.cybercapture.listener.GameJoinLeaveListener;
import de.zugpilot.cybercapture.listener.LobbyListener;
import de.zugpilot.cybercapture.model.game.features.computer.process.CyberProcessRegistry;
import de.zugpilot.cybercapture.model.game.player.CyberPlayerRegistry;
import de.zugpilot.cybercapture.model.game.team.CyberTeamRegistry;
import de.zugpilot.cybercapture.ui.UIHandler;
import lombok.Getter;

@Getter
public class CyberGame {

    private final CyberPlugin cyberPlugin;
    private final CyberPlayerRegistry playerRegistry;
    private final CyberTeamRegistry teamRegistry;
    private final CyberProcessRegistry cyberProcessRegistry;
    private GameState gameState;

    public CyberGame(CyberPlugin cyberPlugin){
        this.cyberPlugin = cyberPlugin;
        this.playerRegistry = new CyberPlayerRegistry(this);
        this.teamRegistry = new CyberTeamRegistry(this);
        this.cyberProcessRegistry = new CyberProcessRegistry(this);
    }

    public void initialize() {
        this.gameState = GameState.LOBBY;
        if(!cyberPlugin.getDataFolder().exists()){
            cyberPlugin.getDataFolder().mkdir();
        }
        getTeamRegistry().load();
        registerListeners();
        registerCommands();
    }

    public void registerCommands(){
        getCyberPlugin().getCommand("cdebug").setExecutor(new DebugCommand(this));
    }

    public void registerListeners(){
        cyberPlugin.getPluginManager().registerEvents(new UIHandler(), cyberPlugin);
        cyberPlugin.getPluginManager().registerEvents(new GameJoinLeaveListener(this), cyberPlugin);
        cyberPlugin.getPluginManager().registerEvents(new LobbyListener(this), cyberPlugin);
    }

    public void disable() {
       getTeamRegistry().unload();
    }

}
