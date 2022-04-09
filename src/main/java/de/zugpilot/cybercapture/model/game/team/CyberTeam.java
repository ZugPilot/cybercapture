package de.zugpilot.cybercapture.model.game.team;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.features.Computer;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class CyberTeam {

    //TODO: Add game specific fields (spawn location, computer field object, door field object)

    /*
    Game fields
     */

    private final Computer computer;
    private double points;

    private CyberTeam lastAttackSent, lastAttackReceived;

    /*
    Default team fields
     */

    private final ItemStack teamIcon;
    private final String coloredTeamName;
    private final String teamIdentifier;
    private final int maxPlayers;
    private final Map<UUID, CyberPlayer> players;
    private CyberTeamState cyberTeamState;

    public CyberTeam(CyberGame cyberGame, ItemStack teamIcon, String coloredTeamName, int maxPlayers){
        this.computer = new Computer(cyberGame,this);
        this.points = 99999; //TODO: Debug value, remember to remove later
        this.teamIcon = teamIcon;
        this.coloredTeamName = coloredTeamName;
        this.teamIdentifier = ChatColor.stripColor(coloredTeamName);
        this.maxPlayers = maxPlayers;
        this.players = new HashMap<>();
        this.cyberTeamState = CyberTeamState.ALIVE;
    }

    public int currentPlayerAmount(){
        return players.keySet().size();
    }

    public void addPlayer(CyberPlayer cyberPlayer){
        this.players.put(cyberPlayer.getPlayer().getUniqueId(), cyberPlayer);
        cyberPlayer.setTeam(this);
    }

    public void removePlayer(CyberPlayer cyberPlayer){
        this.players.remove(cyberPlayer.getPlayer().getUniqueId());
        cyberPlayer.setTeam(null);
    }

    public boolean isPlayer(UUID uuid){
        return players.containsKey(uuid);
    }

    public Optional<CyberPlayer> getPlayer(UUID uuid){
        if(players.containsKey(uuid)){
            return Optional.of(players.get(uuid));
        }
        return Optional.empty();
    }

    public void teamMessage(String message){
        for(UUID uuid : getPlayers().keySet()){
            Player player = Bukkit.getPlayer(uuid);
            if(player == null)return;
            player.sendMessage(message);
        }
    }

    public enum CyberTeamState{
        ALIVE,
        DEAD,
        NOT_PLAYED,
    }

}
