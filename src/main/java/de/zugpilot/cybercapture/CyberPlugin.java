package de.zugpilot.cybercapture;

import de.zugpilot.cybercapture.model.game.CyberGame;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CyberPlugin extends JavaPlugin {

    private PluginManager pluginManager;
    private CyberGame game;
    public static CyberPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        this.pluginManager = Bukkit.getPluginManager();
        this.game = new CyberGame(this);
        game.initialize();
    }

    @Override
    public void onDisable(){
        game.disable();
    }


}
