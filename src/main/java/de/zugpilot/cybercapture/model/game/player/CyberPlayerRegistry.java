package de.zugpilot.cybercapture.model.game.player;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.team.CyberTeamRegistry;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CyberPlayerRegistry {

    private final CyberGame cyberGame;
    private final Map<UUID, CyberPlayer> cyberPlayers;

    public CyberPlayerRegistry(CyberGame cyberGame){
        this.cyberGame = cyberGame;
        this.cyberPlayers = new HashMap<>();
    }

    public void addCyberPlayer(CyberPlayer cyberPlayer){
        this.cyberPlayers.put(cyberPlayer.getPlayer().getUniqueId(), cyberPlayer);
    }

    public void removeCyberPlayer(UUID uuid){
        this.cyberPlayers.remove(uuid);
    }

    public Optional<CyberPlayer> getCyberPlayer(UUID uuid){
        if(cyberPlayers.containsKey(uuid)){
            return Optional.of(cyberPlayers.get(uuid));
        }
        return Optional.empty();
    }

}
