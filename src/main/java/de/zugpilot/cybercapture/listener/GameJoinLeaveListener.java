package de.zugpilot.cybercapture.listener;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;
import java.util.logging.Level;

public class GameJoinLeaveListener implements Listener {

    private final CyberGame cyberGame;

    public GameJoinLeaveListener(CyberGame cyberGame){
        this.cyberGame = cyberGame;
    }

    @EventHandler
    public void onCall(PlayerJoinEvent event){
        event.joinMessage(Component.empty());
        Player player = event.getPlayer();
        CyberPlayer cyberPlayer = new CyberPlayer(player);
        cyberGame.getPlayerRegistry().addCyberPlayer(cyberPlayer);
        switch (cyberGame.getGameState()){
            case LOBBY:
                cyberPlayer.lobby();
                Bukkit.broadcast(Component.text("§a" + player.getName() + " joined the game"));
                break;
            case RUNNING:
                cyberPlayer.spectator();
                Bukkit.broadcast(Component.text("§e" + player.getName() + " joined the game while its running (spectator)"), CyberConstants.PERMISSION_PREFIX + "admin");
                break;
        }
    }

    @EventHandler
    public void onCall(PlayerQuitEvent event){
        event.quitMessage(Component.empty());
        Player player = event.getPlayer();
        Optional<CyberPlayer> optional = cyberGame.getPlayerRegistry().getCyberPlayer(player.getUniqueId());
        if(optional.isEmpty()){
            cyberGame.getCyberPlugin().getLogger().log(Level.WARNING, "Player " + player.getName() + " disconnected without being registered as a CyberPlayer");
            return;
        }
        CyberPlayer cyberPlayer = optional.get();
        switch (cyberGame.getGameState()){
            case LOBBY:
                Bukkit.broadcast(Component.text("§c" + player.getName() + " left the game"));
                break;
            case RUNNING:
                if(cyberPlayer.getTeam().isPresent()){
                    Bukkit.broadcast(Component.text("§c" + player.getName() + " has left the game while it was running"));
                }
                break;
        }
        cyberGame.getPlayerRegistry().removeCyberPlayer(cyberPlayer.getPlayer().getUniqueId());
    }

}
