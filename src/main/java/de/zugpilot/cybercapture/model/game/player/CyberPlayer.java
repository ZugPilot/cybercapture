package de.zugpilot.cybercapture.model.game.player;

import de.zugpilot.cybercapture.model.game.team.CyberTeam;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

@Getter
public class CyberPlayer {

    private final Player player;
    private CyberTeam team;

    public CyberPlayer(Player player){
        this.player = player;
    }

    public void lobby(){
        player.getInventory().clear();
        player.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).displayName("§8» §aSelect team").unbreakable().glow().setLore("§7Right click this compass to select your team", "§8SELECT_TEAM").build());
    }

    public void spectator(){
        player.getInventory().clear();
        player.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).displayName("§8» §aTeleport").unbreakable().glow().setLore("§7Right click this compass and select a player to teleport to", "§8SELECT_TELEPORT_SPECTATOR").build());
    }

    public boolean hasTeam(){
        return team != null;
    }

    public Optional<CyberTeam> getTeam(){
        if(team == null){
            return Optional.empty();
        }
        return Optional.of(team);
    }

    public void setTeam(CyberTeam team){
        this.team = team;
    }

}
